package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.parser.sumo;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Vehicle;
import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.VehicleTimestep;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.ComponentTest;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.IVehicleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Tests for the SUMO trace parser component
 */
@SpringBootTest
public class SumoTraceParserTest extends ComponentTest {

    private ISumoTraceParser sumoTraceParser;

    @Autowired
    private IVehicleService vehicleService;

    @Before
    public void setUp() throws Exception {
        sumoTraceParser = new SumoTraceParser(vehicleService);
    }

    @Test
    public void testParseInput() throws Exception {
        assertThat(vehicleService.findAll().size()).isEqualTo(0);

        File sumoTrace = ResourceUtils.getFile("classpath:sumo-sample-trace.xml");
        InputStream inputStream = new FileInputStream(sumoTrace);
        sumoTraceParser.parse(inputStream);

        assertThat(vehicleService.findAll().size()).isEqualTo(34);

        Vehicle vehicleTen = vehicleService.findOne(10L);

        List<VehicleTimestep> vehicleTimestepList = vehicleTen.getVehicleTimesteps();
        assertThat(vehicleTimestepList.get(0).getData().getSpeed()).isEqualTo(0);
        assertThat(vehicleTimestepList.get(vehicleTimestepList.size()-1).getData().getLatitude()).isEqualTo(48.173777);
        assertThat(vehicleTimestepList.get(vehicleTimestepList.size()-1).getData().getSpeed()).isEqualTo(0.513289f);
    }
}

package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.dao;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DataJpaTest
public class TimestepTest extends DaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IVehicleDao vehicleDao;

    @Autowired
    private ITimestepDao timestepDao;

    @Before
    public void setUp() throws Exception {
        addTimestepEntry(0L, 0.0, 16.367211, 48.195761, 0f);
        addTimestepEntry(1L, 0.0, 16.365407, 48.217178, 0f);

        addTimestepEntry(0L, 0.1, 16.367211, 48.195761, 0.248417f);
        addTimestepEntry(1L, 0.1, 16.365487, 48.217188, 0.250738f);
        addTimestepEntry(2L, 0.1, 16.374918, 48.191782, 0f);
    }

    private void addTimestepEntry(Long vehicleId, Double time, Double latitude, Double longitude, Float speed) {
        Vehicle vehicle = vehicleDao.findOne(vehicleId);
        if (vehicle == null) {
            vehicle = new Vehicle();
            vehicle.setId(vehicleId);
        }

        Timestep timestep = timestepDao.findOneByTime(time);
        if (timestep == null) {
            timestep = new Timestep();
            timestep.setTime(time);
            timestep = timestepDao.save(timestep);
        }

        // timestep data
        Data data = new Data();
        data.setLatitude(latitude);
        data.setLongitude(longitude);
        data.setSpeed(speed);

        VehicleTimestep vehicleTimestep = new VehicleTimestep();
        vehicleTimestep.setData(data);
        vehicleTimestep.setVehicle(vehicle);
        vehicleTimestep.setTimestep(timestep);

        vehicle.getVehicleTimesteps().add(vehicleTimestep);
        vehicleDao.save(vehicle);
    }

    @Test
    public void addVehicleAndTimestepTest() throws Exception {
        assertThat(vehicleDao.findOne(0L).getVehicleTimesteps().size()).isEqualToComparingFieldByField(2);
        assertThat(vehicleDao.findOne(1L).getVehicleTimesteps().size()).isEqualToComparingFieldByField(2);
        assertThat(vehicleDao.findOne(2L).getVehicleTimesteps().size()).isEqualToComparingFieldByField(1);
    }

    @Test
    public void checkForCorrectTimestepData() throws Exception {
        List<VehicleTimestep> vehicleTimesteps = vehicleDao.findOne(1L).getVehicleTimesteps();
        for (VehicleTimestep vt : vehicleTimesteps) {
            if (vt.getTimestep().getTime().equals(0.0)) {
                assertThat(vt.getData().getLatitude()).isEqualTo(16.365407);
                assertThat(vt.getData().getLongitude()).isEqualTo(48.217178);
            } else if (vt.getTimestep().getTime().equals(0.1)) {
                assertThat(vt.getData().getLatitude()).isEqualTo(16.365487);
                assertThat(vt.getData().getLongitude()).isEqualTo(48.217188);
            } else {
                assert false;
            }
        }
        assertThat(vehicleTimesteps.size()).isGreaterThan(0);
    }
}

package at.ac.tuwien.tschmidleithner.edgemonitoring.cloudlet.controller;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Data;
import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.dto.VehicleDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EndpointController.class)
public class EndpointControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testEndpointWithVehicle() throws Exception {
        Data data = new Data();
        data.setSpeed(0.5234f);
        data.setLatitude(16.367211);
        data.setLongitude(48.195761);

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(1L);
        vehicleDto.setRequestDate(new Date());
        vehicleDto.setData(data);

        this.mvc.perform(post("/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(vehicleDto)))
                    .andExpect(status().isOk());
    }
}

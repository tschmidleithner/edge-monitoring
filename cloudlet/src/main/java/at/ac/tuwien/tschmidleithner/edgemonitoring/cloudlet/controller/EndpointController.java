package at.ac.tuwien.tschmidleithner.edgemonitoring.cloudlet.controller;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.dto.VehicleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Provides the endpoint for a Cloudlet that uses vehicle inputs
 */
@RestController
public class EndpointController {

    protected Logger log = Logger.getLogger(this.getClass().getName());

    @RequestMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public void postVehicle(@RequestBody VehicleDto vehicle) {
        log.info("Received vehicle: " + vehicle.toString());
    }
}

package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.simulator.impl;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Timestep;
import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.VehicleTimestep;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.simulator.ISimulator;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.ITimestepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

@Component
public class SimulatorImpl implements ISimulator {

    private AtomicBoolean isCanceled = new AtomicBoolean(false);
    private Logger log = Logger.getLogger(this.getClass().getName());
    private ITimestepService timestepService;

    @Autowired
    public SimulatorImpl(ITimestepService timestepService) {
        this.timestepService = timestepService;
    }

    @Override
    public void startSimulation() {
        log.info("Simulation started... loading timestep entities");
        Sort sortByTimeASC = new Sort(Sort.Direction.ASC, "time");
        List<Timestep> timesteps = timestepService.findAll(sortByTimeASC);
        log.info("Timesteps loaded. Starting with REST requests...");

        for (Timestep timestep : timesteps) {
            if (isCanceled.get()) {
                return;
            }

            log.info(String.format("Start timestep ", timestep.getTime()));

            for (VehicleTimestep vehicleTimestep : timestep.getVehicleTimesteps()) {
                log.info(String.format("Communicating current position of vehicle %s", vehicleTimestep.getVehicle().getId()));
                // TODO: add REST request
            }

            log.info(String.format("Timestep %f finished", timestep.getTime()));
        }
    }

    @Override
    public void stopSimulation() {
        isCanceled.set(true);
    }
}

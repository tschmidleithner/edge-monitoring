package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Vehicle;

/**
 * Service for persisting vehicles
 */
public interface IVehicleService extends ICrudService<Vehicle, Long> {

    /**
     * Adds a VehicleTimestep entry by reusing reusing vehicles and timesteps if already persisted.
     *
     * @param vehicleId
     * @param time
     * @param latitude
     * @param longitude
     * @param speed
     */
    Vehicle addTimestepEntry(Long vehicleId, Double time, Double latitude, Double longitude, Float speed);
}

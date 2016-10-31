package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.dao;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO interface for persisting vehicles
 */
public interface IVehicleDao extends JpaRepository<Vehicle, Long> {
}


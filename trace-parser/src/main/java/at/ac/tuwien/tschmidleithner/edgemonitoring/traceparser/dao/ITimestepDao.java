package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.dao;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Timestep;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO interface for persisting timesteps
 */
public interface ITimestepDao extends JpaRepository<Timestep, Long> {
    Timestep findOneByTime(Double time);
}


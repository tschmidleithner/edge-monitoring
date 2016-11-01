package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.impl;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Timestep;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.dao.ITimestepDao;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.AbstractService;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.ITimestepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TimestepService extends AbstractService<Timestep, Long> implements ITimestepService {

    private ITimestepDao timestepDao;

    @Autowired
    public TimestepService(ITimestepDao timestepDao) {
        this.timestepDao = timestepDao;
    }

    @Override
    protected JpaRepository<Timestep, Long> getRepository() {
        return timestepDao;
    }
}

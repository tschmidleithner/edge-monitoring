package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.impl;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Vehicle;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.dao.IVehicleDao;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.AbstractService;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehicleService extends AbstractService<Vehicle, Long> implements IVehicleService {

    private IVehicleDao vehicleDao;

    @Autowired
    public VehicleService(IVehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Override
    protected PagingAndSortingRepository<Vehicle, Long> getRepository() {
        return vehicleDao;
    }
}

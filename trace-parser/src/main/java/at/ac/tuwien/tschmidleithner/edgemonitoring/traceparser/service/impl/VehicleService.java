package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.impl;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Data;
import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Timestep;
import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Vehicle;
import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.VehicleTimestep;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.dao.ITimestepDao;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.dao.IVehicleDao;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.AbstractService;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehicleService extends AbstractService<Vehicle, Long> implements IVehicleService {

    private IVehicleDao vehicleDao;
    private ITimestepDao timestepDao;

    @Autowired
    public VehicleService(IVehicleDao vehicleDao, ITimestepDao timestepDao) {
        this.vehicleDao = vehicleDao;
        this.timestepDao = timestepDao;
    }

    @Override
    protected JpaRepository<Vehicle, Long> getRepository() {
        return vehicleDao;
    }

    @Transactional
    @Override
    public Vehicle addTimestepEntry(Long vehicleId, Double time, Double latitude, Double longitude, Float speed) {
        Vehicle vehicle = this.findOne(vehicleId);
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
        return this.save(vehicle);
    }
}

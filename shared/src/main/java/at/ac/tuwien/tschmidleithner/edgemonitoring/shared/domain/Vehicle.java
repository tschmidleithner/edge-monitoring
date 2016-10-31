package at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vehicle implements IEntity<Long> {
    @Id
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.vehicle", cascade=CascadeType.ALL)
    private List<VehicleTimestep> vehicleTimesteps = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<VehicleTimestep> getVehicleTimesteps() {
        return vehicleTimesteps;
    }

    public void setVehicleTimesteps(List<VehicleTimestep> vehicleTimesteps) {
        this.vehicleTimesteps = vehicleTimesteps;
    }
}

package at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Timestep implements IEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private Double time;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id.timestep")
    private List<VehicleTimestep> vehicleTimesteps = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public List<VehicleTimestep> getVehicleTimesteps() {
        return vehicleTimesteps;
    }

    public void setVehicleTimesteps(List<VehicleTimestep> vehicleTimesteps) {
        this.vehicleTimesteps = vehicleTimesteps;
    }
}

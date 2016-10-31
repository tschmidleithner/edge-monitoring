package at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain;

import javax.persistence.*;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "id.vehicle",
                joinColumns = @JoinColumn(name = "VEHICLE_ID")),
        @AssociationOverride(name = "id.timestep",
                joinColumns = @JoinColumn(name = "TIMESTEP_ID")) })
public class VehicleTimestep implements IEntity<VehicleTimestepId> {

    @EmbeddedId
    private VehicleTimestepId id = new VehicleTimestepId();

    @Embedded
    private Data data;

    @Override
    public VehicleTimestepId getId() {
        return id;
    }

    public void setVehicleTimestepId(VehicleTimestepId id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Vehicle getVehicle() {
        return id.getVehicle();
    }

    public void setVehicle(Vehicle vehicle) {
        id.setVehicle(vehicle);
    }

    public Timestep getTimestep() {
        return id.getTimestep();
    }

    public void setTimestep(Timestep timestep) {
        id.setTimestep(timestep);
    }
}

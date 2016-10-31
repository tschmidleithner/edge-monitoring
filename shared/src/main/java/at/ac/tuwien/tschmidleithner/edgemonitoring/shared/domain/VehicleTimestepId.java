package at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class VehicleTimestepId implements Serializable {

    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Timestep timestep;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Timestep getTimestep() {
        return timestep;
    }

    public void setTimestep(Timestep timestep) {
        this.timestep = timestep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleTimestepId that = (VehicleTimestepId) o;

        if (!vehicle.equals(that.vehicle)) return false;
        return timestep.equals(that.timestep);
    }

    @Override
    public int hashCode() {
        int result = vehicle.hashCode();
        result = 31 * result + timestep.hashCode();
        return result;
    }
}

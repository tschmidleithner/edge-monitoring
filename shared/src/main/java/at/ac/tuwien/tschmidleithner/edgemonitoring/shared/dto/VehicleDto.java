package at.ac.tuwien.tschmidleithner.edgemonitoring.shared.dto;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Data;

import java.util.Date;

/**
 * DTO object which represents a vehicle at a specific request date
 */
public class VehicleDto {
    private Long id;
    private Date requestDate;
    private Data data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "VehicleDto{" +
                "id=" + id +
                ", requestDate=" + requestDate +
                ", data=" + data +
                '}';
    }
}

package com.musala.drones.vm.response;

import com.musala.drones.type.Model;
import com.musala.drones.type.State;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class DroneResponse {
    private String serialNumber;
    private Model model;
    private Double weightLimit;
    private BigDecimal batteryCapacity;
    private State state;

    public DroneResponse(String serialNumber, Model model, double weightLimit, BigDecimal batteryCapacity,
            State state) {
        super();
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }
}

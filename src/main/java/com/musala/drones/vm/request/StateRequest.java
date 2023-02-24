package com.musala.drones.vm.request;

import com.musala.drones.type.State;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StateRequest {
    @NotNull(message = "{constraints.drone.serialnumber.notblank}")
    private String serialNumber;
    @NotNull(message = "{constraints.drone.state.notblank}")
    private State droneState;
}

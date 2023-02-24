package com.musala.drones.vm.request;

import com.musala.drones.type.State;
import lombok.Data;

@Data
public class StateRequest {
	private String serialNumber;
	private State droneState;
}

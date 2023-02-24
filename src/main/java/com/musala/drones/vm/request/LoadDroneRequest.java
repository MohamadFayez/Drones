package com.musala.drones.vm.request;

import com.musala.drones.entity.Medication;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class LoadDroneRequest {
	@NotNull(message = "{constraints.drone.serialnumber.notblank}")
	private String serialNumber;
	private List<Medication> items;
}

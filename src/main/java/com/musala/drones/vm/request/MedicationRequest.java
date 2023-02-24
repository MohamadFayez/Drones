package com.musala.drones.vm.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MedicationRequest {

	@NotNull(message = "Name is required")
	private String name;
	@NotNull(message = "Weight is required")
	private Double weight;
	private byte [] image;
	@NotNull(message = "Code is required")
	private String code;
}


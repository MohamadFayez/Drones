package com.musala.drones.vm.request;

import com.musala.drones.type.Model;
import com.musala.drones.type.State;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class DroneRequest {
	public DroneRequest() {

	}

	public DroneRequest(String serialNumber, Model model, double weightLimit, BigDecimal batteryCapacity,
			State state) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}

	@NotNull
	@Length(min=1 ,max = 100)
	private String serialNumber;

	@Enumerated
	@Column(name = "MODEL", nullable = false)
	private Model model;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "500.0", inclusive = true)
	private Double weightLimit;

	@NotNull
	@Range(min = 0, max = 100)
	private BigDecimal batteryCapacity;

	@Enumerated
	private State state;

}

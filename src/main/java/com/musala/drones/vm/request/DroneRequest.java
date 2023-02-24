package com.musala.drones.vm.request;

import com.musala.drones.type.Model;
import com.musala.drones.type.State;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
public class DroneRequest {


    @NotNull(message = "{constraints.drone.serialnumber.notblank}")
    @Length(min = 1, max = 100, message = "{constraints.drone.serialnumber.length}")
    private String serialNumber;
    @NotNull(message="{constraints.drone.weight.notnull}")
	@DecimalMin(value = "0.0", inclusive = true,message="{constraints.drone.weight.min}")
	@DecimalMax(value = "500.0", inclusive = true,message="{constraints.drone.weight.max}")
    private Double weightLimit;
    @NotNull(message="{constraints.drone.batteryCapacity.notnull}")
	@Range(min = 0, max = 100,message="{constraints.drone.batteryCapacity.range}")
    private Double batteryCapacity;
   @NotNull(message="{constraints.drone.model.notnull}")
    private Model model;
    @NotNull(message="{constraints.drone.state.notnull}")
    private State state;

}

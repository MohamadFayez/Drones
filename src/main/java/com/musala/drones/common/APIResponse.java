package com.musala.drones.common;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
public class APIResponse <Object>{

	private int code;
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;
}



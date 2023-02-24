package com.musala.drones.vm.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationResponse {


    private String code;
    private String name;
    private Double weight;

}

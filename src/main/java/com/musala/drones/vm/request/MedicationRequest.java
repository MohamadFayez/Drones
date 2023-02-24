package com.musala.drones.vm.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MedicationRequest {

    @NotNull(message = "{constraints.medication.name.notblank}")
    @Pattern(regexp = "^[a-zA-Z0-9_-]*[a-zA-Z0-9][a-zA-Z0-9_-]*$", flags = Pattern.Flag.UNICODE_CASE, message = "{constraints.medication.name.pattern}")
    private String name;
    @NotNull(message = "{constraints.medication.weight.notblank}")
    private Double weight;
    private byte[] image;
    @NotNull(message = "{constraints.medication.code.notblank}")
    @Pattern(regexp = "^[A-Z0-9_]*[A-Z0-9][A-Z0-9_]*$", flags = Pattern.Flag.UNICODE_CASE, message = "{constraints.medication.code.pattern}")
    private String code;
}


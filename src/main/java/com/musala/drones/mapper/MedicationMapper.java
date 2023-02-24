package com.musala.drones.mapper;

import com.musala.drones.entity.Medication;
import com.musala.drones.vm.request.MedicationRequest;
import com.musala.drones.vm.response.MedicationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {


    Medication toEntityModel(MedicationRequest medicationRequest);


    List<Medication> toEntityModel(List<MedicationRequest> medicationRequests);

    MedicationResponse toViewModel(Medication medication);

    List<MedicationResponse> toViewModel(List<Medication> medications);


}

package com.musala.drones.controller;

import com.musala.drones.common.APIResponse;
import com.musala.drones.entity.Medication;
import com.musala.drones.mapper.MedicationMapper;
import com.musala.drones.service.MedicationService;
import com.musala.drones.vm.request.MedicationRequest;
import com.musala.drones.vm.response.MedicationResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${app.config.medications.base-uri}")
@Validated
public class MedicationController {

    @Autowired
    private MedicationService medicationSerivce;

    @Autowired
    private MedicationMapper medicationMapper;

    @Tag(name = "Register new medication")
    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping(value = "${app.config.medications.api.register}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerMedication(@Valid @RequestPart("request") MedicationRequest medicationRequest,
            @RequestPart("image") MultipartFile image) throws IOException {

        Medication medication = medicationMapper.toEntityModel(medicationRequest);
        medication = this.medicationSerivce.registerMedication(medication, image);

        MedicationResponse response = medicationMapper.toViewModel(medication);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }


    @Tag(name = "Check drone loaded medication items")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "${app.config.medications.api.check-loaded-item}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLoadedItems(@Valid @PathVariable String droneSerialNumber) {

        List<Medication> medications = medicationSerivce.checkLoadedMedicationItem(droneSerialNumber);

        List<MedicationResponse> response = medicationMapper.toViewModel(medications);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }

}

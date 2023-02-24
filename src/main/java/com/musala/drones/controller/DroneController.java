package com.musala.drones.controller;


import com.musala.drones.common.APIResponse;
import com.musala.drones.entity.Drone;
import com.musala.drones.mapper.DroneMapper;
import com.musala.drones.service.DroneService;
import com.musala.drones.vm.request.DroneRequest;
import com.musala.drones.vm.request.LoadDroneRequest;
import com.musala.drones.vm.request.StateRequest;
import com.musala.drones.vm.response.DroneResponse;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("${app.config.drones.base-uri}")
@Validated
public class DroneController {

    @Autowired
    private DroneMapper droneMapper;
    @Autowired
    private DroneService droneService;


    @Tag(name = "Register new drone")
    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping(value = "${app.config.drones.api.register}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerDrone(@Valid @NotNull @RequestBody DroneRequest dronerequest) {
        Drone drone = droneMapper.toEntityModel(dronerequest);
        drone = droneService.registerDrone(drone);
        DroneResponse response = droneMapper.toViewModel(drone);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }

    @Tag(name = "Get available drone for load")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "${app.config.drones.api.available}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAvailableDroneForLoading() {
        List<Drone> drones = droneService.getAvailabeDrones();
        List<DroneResponse> response = droneMapper.toViewModel(drones);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }

    @Tag(name = "Check battery level for given drone")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "${app.config.drones.api.batterylevel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkBatteryLevelForGivenDrone(@PathVariable("serialNumber") String serialNumber) {
        Drone drone = droneService.getBatteryLevelForGivenDrone(serialNumber);
        DroneResponse response = droneMapper.toViewModel(drone);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }


    @Tag(name = "Get all registered drones")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "${app.config.drones.api.alldrones}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRegisteredDrone() {
        List<Drone> drones = droneService.findAll();
        List<DroneResponse> response = droneMapper.toViewModel(drones);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }

    @Tag(name = "Load drone with medication items")
    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping(value = "${app.config.drones.api.loaddrone}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loadDroneWithMedicationItems(@Valid @RequestBody LoadDroneRequest request) {
        Drone drone = droneService.loadingDroneWithMedicationItem(request);
        DroneResponse response = droneMapper.toViewModel(drone);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }


    @Tag(name = "Update drone status")
    @ApiResponse(responseCode = "200", description = "Success")
    @PutMapping(value = "${app.config.drones.api.updatestatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDroneState(@Valid @RequestBody StateRequest request) {
        Drone drone = droneService.updateDroneState(request.getSerialNumber(), request.getDroneState());
        DroneResponse response = droneMapper.toViewModel(drone);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }


}

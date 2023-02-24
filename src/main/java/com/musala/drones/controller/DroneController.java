package com.musala.drones.controller;


import com.musala.drones.common.APIResponse;
import com.musala.drones.entity.Drone;
import com.musala.drones.mapper.DroneMapper;
import com.musala.drones.service.impl.DroneSeriviceImpl;
import com.musala.drones.vm.request.DroneRequest;
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
    private DroneSeriviceImpl droneService;


    @Tag(name = "register new drone")
    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping(value = "${app.config.drones.api.register}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerDrone(@Valid @NotNull @RequestBody DroneRequest dronerequest) {
        Drone drone = droneMapper.toEntityModel(dronerequest);
        drone = droneService.registerDrone(drone);
        DroneResponse response = droneMapper.toViewModel(drone);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }

    @Tag(name = "get available drone")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "${app.config.drones.api.available}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAvailableDroneForLoading() {
        List<Drone> drones = droneService.getAvailabeDrones();
        List<DroneResponse> response = droneMapper.toViewModel(drones);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }

    @PostMapping(path = "/battery/{serialNumber}")

    @Tag(name = "check battery level for given drone")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "${app.config.drones.api.batterylevel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkBatteryLevelForGivenDrone(@PathVariable("serialNumber") String serialNumber) {
        Drone drone = droneService.getBatteryLevelForGivenDrone(serialNumber);
        DroneResponse response = droneMapper.toViewModel(drone);
        return ResponseEntity.ok().body(APIResponse.builder().code(HttpStatus.OK.value()).data(response).build());
    }


}

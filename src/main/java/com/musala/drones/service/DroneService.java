package com.musala.drones.service;


import com.musala.drones.entity.Drone;
import com.musala.drones.type.State;
import com.musala.drones.vm.request.LoadDroneRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public interface DroneService {

    Drone registerDrone(Drone drone);

    Drone getBatteryLevelForGivenDrone(String serialNumber);

    List<Drone> getAvailabeDrones();

    Optional<Drone> findBySerialNumber(String serialNumber);

    Drone loadingDroneWithMedicationItem(LoadDroneRequest loadDroneRequest);


    List<Drone> findAll();

     double checkBatteryLevel(String serialNumber);

     Drone updateDroneState(String serialNumber, State state);

}

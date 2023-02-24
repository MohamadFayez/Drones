package com.musala.drones.service;


import java.util.List;

import com.musala.drones.entity.Drone;
import org.springframework.stereotype.Component;


@Component
public interface DroneService {

	Drone registerDrone(Drone drone);

	Drone getBatteryLevelForGivenDrone(String serialNumber);

	List<Drone> getAvailabeDrones();


}

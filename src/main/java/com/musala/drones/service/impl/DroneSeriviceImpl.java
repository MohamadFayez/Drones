package com.musala.drones.service.impl;


import com.musala.drones.entity.Drone;
import com.musala.drones.exception.BusinessException;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.DroneService;
import com.musala.drones.type.State;
import com.musala.drones.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneSeriviceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public Drone registerDrone(Drone drone) {
        if (!ValidationUtil.validateDroneBatteryCapacity(drone.getBatteryCapacity()))
            throw new BusinessException("validation.drone.batteryCapacity.invalid");

        if (!ValidationUtil.validateDroneWeightLimit(drone.getWeightLimit()))
            throw new BusinessException("validation.drone.invalidLimit");


        if (!drone.getState().equals(State.LOADING))
            throw new BusinessException("validation.droneMedicationLoad.notloading");
        if (drone.getBatteryCapacity() < 25) {
            throw new BusinessException("validation.drone.batteryCapacity.low");
        }
        Drone newDrone = Drone.builder().serialNumber(drone.getSerialNumber()).weightLimit(drone.getWeightLimit())
                .batteryCapacity(drone.getBatteryCapacity()).model(drone.getModel()).state(drone.getState()).build();

        return droneRepository.save(newDrone);
    }

    @Override
    public Drone getBatteryLevelForGivenDrone(String serialNumber) {

        Drone newdrone = new Drone();
        newdrone.setSerialNumber(serialNumber);
        Drone drone = droneRepository.findBySerialNumber(newdrone.getSerialNumber());
        if (drone == null) {
            throw new BusinessException("validation.drone.notfound");
        }
        return drone;
    }

    @Override
    public List<Drone> getAvailabeDrones() {
        return droneRepository.findAllByState(State.IDLE);
    }

}

package com.musala.drones.service.impl;


import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Medication;
import com.musala.drones.exception.BusinessException;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.DroneService;
import com.musala.drones.type.State;
import com.musala.drones.validation.ValidationUtil;
import com.musala.drones.vm.request.LoadDroneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Drone> drone = droneRepository.findBySerialNumber(newdrone.getSerialNumber());
        if (drone.isEmpty()) {
            throw new BusinessException("validation.drone.notfound");
        }
        return drone.get();
    }

    @Override
    public List<Drone> getAvailabeDrones() {
        return droneRepository.findAllByState(State.IDLE);
    }

    @Override
    public Optional<Drone> findBySerialNumber(String number) {
        return droneRepository.findBySerialNumber(number);
    }

    @Override
    public Drone loadingDroneWithMedicationItem(LoadDroneRequest drone) {

        Drone dronedb = findBySerialNumber(drone.getSerialNumber()).orElseThrow(
                () -> new BusinessException("validation.drone.notfound", new Object[]{drone.getSerialNumber()}));
        List<Double> itemWeights = drone.getItems().stream().map(Medication::getWeight).collect(Collectors.toList());

        double allItemsWeightSum = itemWeights.stream().mapToDouble(Double::doubleValue).sum();

        if (allItemsWeightSum > dronedb.getWeightLimit())
            throw new BusinessException("validation.drone.exceedLimit",
                    new Object[]{drone.getSerialNumber(), dronedb.getWeightLimit()});
        if (dronedb.getBatteryCapacity() < 25) {
            throw new BusinessException("validation.drone.batteryCapacity.low");
        }
        dronedb.setState(State.LOADING);

        List<Medication> medications = drone.getItems().stream()
                .map(m -> new Medication(m.getCode(), m.getName(), m.getWeight(), m.getImage()))
                .collect(Collectors.toList());

        if (allItemsWeightSum == dronedb.getWeightLimit())
            dronedb.setState(State.LOADED);

        dronedb.setMedications(medications);
        return save(dronedb);
    }

    @Override
    public List<Drone> findAll() {
        return droneRepository.findAll();
    }

    @Override
	public double checkBatteryLevel(String serialNumber) {
		Drone drone = findBySerialNumber(serialNumber)
				.orElseThrow(() -> new BusinessException("validation.drone.notfound", new Object[]{serialNumber}));

		List<Double> itemWeights = drone.getMedications().stream().map(Medication::getWeight)
				.collect(Collectors.toList());

		Double medicationSum = drone.getMedications().isEmpty() ? 0
				: itemWeights.stream().mapToDouble(Double::doubleValue).sum();

		return ((drone.getWeightLimit() - medicationSum) / 5);
	}

    @Override
    public Drone updateDroneState(String serialNumber, State state) {
        Drone drone = findBySerialNumber(serialNumber)
                .orElseThrow(() -> new BusinessException("validation.drone.notfound",new Object[]{serialNumber}));

        if (State.LOADING.equals(state)
                && checkBatteryLevel(drone.getSerialNumber()) < 25)
            throw new BusinessException("validation.drone.batteryCapacity.low");
        drone.setState(state);
        return save(drone);
    }


    public Drone save(Drone drone) {
        return droneRepository.save(drone);
    }

}

package com.musala.drones.repository;

import java.util.List;

import com.musala.drones.entity.Drone;
import com.musala.drones.type.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {

	List<Drone> findAllByState(State state);

	Drone findBySerialNumber(String serialNumber);

	default void updateState(Drone drone, State state) {
		drone.setState(State.LOADING);
		this.save(drone);
	}

}

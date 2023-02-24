package com.musala.drones.repository;

import java.util.List;
import java.util.Optional;

import com.musala.drones.entity.Drone;
import com.musala.drones.type.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {

	List<Drone> findAllByState(State state);

	Optional<Drone> findBySerialNumber(String serialNumber);

	default void updateState(Drone drone, State state) {
		drone.setState(State.LOADING);
		this.save(drone);
	}

}

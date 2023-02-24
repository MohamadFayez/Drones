package com.musala.drones.repository;

import com.musala.drones.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, String> {

	Optional<Medication> findByCode(String code);

	List<Medication> findByName(String name);

}

package com.musala.drones.service;

import com.musala.drones.entity.Medication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MedicationService {
Medication registerMedication(Medication medication, MultipartFile file) throws IOException;

	List<Medication> checkLoadedMedicationItem(String droneSerialNumber);
}

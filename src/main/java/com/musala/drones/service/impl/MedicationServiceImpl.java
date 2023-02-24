package com.musala.drones.service.impl;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Medication;
import com.musala.drones.exception.BusinessException;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.service.DroneService;
import com.musala.drones.service.MedicationService;
import com.musala.drones.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private DroneService droneService;

    @Override
    public Medication registerMedication(Medication medication, MultipartFile file) throws IOException {
        byte[] imagebytes = ImageUtil.processImage(file);
        medication.setImage(imagebytes);
        return this.medicationRepository.save(medication);
    }

    @Override
    public List<Medication> checkLoadedMedicationItem(String droneSerialNumber) {
        Optional<Drone> drone = droneService.findBySerialNumber(droneSerialNumber);
        if (drone.isEmpty()) {
            throw new BusinessException("validation.drone.notfound", new Object[]{droneSerialNumber});
        } else {
            return drone.get().getMedications();
        }
    }

}

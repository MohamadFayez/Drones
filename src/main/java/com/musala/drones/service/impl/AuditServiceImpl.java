package com.musala.drones.service.impl;

import com.musala.drones.entity.Audit;
import com.musala.drones.repository.AuditRepository;
import com.musala.drones.service.AuditService;
import com.musala.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private DroneService droneService;
    @Autowired
    private AuditRepository auditRepository;


    public void checkDroneBatteryLevel() {

        droneService.findAll().forEach(m -> {
            double capacity = droneService.checkBatteryLevel(m.getSerialNumber());
            Audit audit = Audit.builder().droneBatteryLevel(capacity).serialNumber(m.getSerialNumber()).build();
            auditRepository.save(audit);
        });
    }
}

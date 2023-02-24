package com.musala.drones.schedule;

import com.musala.drones.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DroneBatteryAuditScheduler {

    @Autowired
    private AuditService auditService;

    @Scheduled(fixedRate = 10000)
    public void logBatteryCapacity() {
        auditService.checkDroneBatteryLevel();
    }
}

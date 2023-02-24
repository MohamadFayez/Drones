package com.musala.drones.repository;

import com.musala.drones.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, String> {

}

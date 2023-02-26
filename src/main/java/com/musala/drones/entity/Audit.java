package com.musala.drones.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AUDIT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Audit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "serial_number", length = 100, nullable = false)
	private String serialNumber;
	@Column(name = "drone_battery_level", length = 100, nullable = false)
	private double droneBatteryLevel;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@PrePersist
	private void setCreatedAt() {
		createdAt = new Date();
	}

}

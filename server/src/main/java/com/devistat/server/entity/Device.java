package com.devistat.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "devices")
public class Device {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "device_code")
	 Integer deviceCode;
	 
	 @Column(name = "device_name")
	 String deviceName;
	 
	 public Device(String deviceName) {
		 this.deviceName = deviceName;
	 }
}
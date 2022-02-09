package com.devistat.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
@NoArgsConstructor
@Entity
public class Device {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer deviceCode;
	 
	 private String deviceName;
	 
	 @OneToMany(mappedBy="device", cascade = CascadeType.ALL)
	 private List<DeviceStatus> deviceStatuses;

	 public Device(String deviceName) {
		 this.deviceName = deviceName;
	 }

	 public Device(Integer deviceCode,
			 	   String deviceName) {
		 this.deviceCode = deviceCode;
		 this.deviceName = deviceName;
	 }
	 
	 public Device(Integer deviceCode,
			 	   String deviceName,
			 	   List<DeviceStatus> deviceStatuses) {
	 this.deviceCode = deviceCode;
	 this.deviceName = deviceName;
	 this.deviceStatuses = deviceStatuses;
}
}
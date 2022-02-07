package com.devistat.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "device_statuses")
public class DeviceStatus {
	 @JsonIgnore
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "device_status_code")
	 Integer deviceStatusCode;
	 
	 @Column(name = "device_status_cpu")
	 //String deviceStatusCpu;
	 String cpu;
	 
	 @Column(name = "device_status_memory")
	 //String deviceStatusMemory;
	 String memory;
	 
	 @Column(name = "device_status_disk")
	 //String deviceStatusDisk;
	 String disk;
	 
	 //@Column(name = "device_status_process")
	 //String deviceStatusProcess;
	 
	 //@Column(name = "device_status_service")
	 //String deviceStatusService;

	 @Column(name = "device_status_sensor")
	 //String deviceSensor;
	 String sensor;
	 
	 @Column(name = "device_status_time_spent")
	 //String deviceTimeSpent;
	 String timeSpent;
	 
	 @Column(name = "device_status_user")
	 //String deviceUser;
	 String user;
	 
	 public DeviceStatus(
 			 String cpu,
			 String memory,
			 String disk,
			 //String deviceStatusProcess,
			 //String deviceStatusService,
			 String sensor,
			 String timeSpent,
			 String user
//	 			 String deviceStatusCpu,
//				 String deviceStatusMemory,
//				 String deviceStatusDisk,
//				 //String deviceStatusProcess,
//				 //String deviceStatusService,
//				 String deviceSensor,
//				 String deviceTimeSpent,
//					 String deviceUser
						 ) {
		 this.cpu = cpu;
		 this.memory = memory;
		 this.disk = disk;
		 //this.deviceStatusProcess = deviceStatusProcess;
		 //this.deviceStatusService = deviceStatusService;
		 this.sensor = sensor;
		 this.timeSpent = timeSpent;
		 this.user = user;
		 
//		 this.deviceStatusCpu = deviceStatusCpu;
//		 this.deviceStatusMemory = deviceStatusMemory;
//		 this.deviceStatusDisk = deviceStatusDisk;
//		 //this.deviceStatusProcess = deviceStatusProcess;
//		 //this.deviceStatusService = deviceStatusService;
//		 this.deviceSensor = deviceSensor;
//		 this.deviceTimeSpent = deviceTimeSpent;
//		 this.deviceUser = deviceUser;
	 }
}
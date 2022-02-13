package com.devistat.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@Entity
public class DeviceStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer deviceStatusCode;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime loggedTime;
	
	@ManyToOne
	@JoinColumn(name = "deviceCode")
	private Device device;
	
	@OneToOne(mappedBy="deviceStatus", cascade = CascadeType.ALL)
	private DeviceStatusCpu cpu;
	
	@OneToOne(mappedBy="deviceStatus", cascade = CascadeType.ALL)
	private DeviceStatusMemory memory;
	
	@OneToOne(mappedBy="deviceStatus", cascade = CascadeType.ALL)
	private DeviceStatusDisk disk;
	
	@OneToOne(mappedBy="deviceStatus", cascade = CascadeType.ALL)
	private DeviceStatusNetwork network;
	
	public DeviceStatus(
			LocalDateTime loggedTime,
			Device device) {
		this.loggedTime = loggedTime;
		this.device = device;
	}
	
	public void deviceStatusSetter(
			DeviceStatusCpu cpu,
			DeviceStatusMemory memory,
			DeviceStatusDisk disk,
			DeviceStatusNetwork network) {
		this.cpu = cpu;
		this.memory = memory;
		this.disk = disk;
		this.network = network;
	}

    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	stringBuilder.append("loggedTime : " + this.loggedTime + "\n");
    	stringBuilder.append("device : " + this.device + "\n");
    	stringBuilder.append("cpu : " + this.cpu + "\n");
    	stringBuilder.append("memory : " + this.memory + "\n");
    	stringBuilder.append("disk : " + this.disk + "\n");
    	stringBuilder.append("network : " + this.network + "\n");
    	
    	return stringBuilder.toString();
    }
}
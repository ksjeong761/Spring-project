package com.devistat.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@Entity
public class DeviceStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deviceStatusCode;
	
	private LocalDateTime timestamp;
	
	@ManyToOne
    @JoinColumn(name = "deviceCode")
	private Device device;
	
	@OneToOne
	@JoinColumn(name = "deviceStatusCpuCode")
	private DeviceStatusCpu cpu;

	@OneToOne
	@JoinColumn(name = "deviceStatusMemoryCode")
	private DeviceStatusMemory memory;
	
	@OneToOne
	@JoinColumn(name = "deviceStatusDiskCode")
	private DeviceStatusDisk disk;
	
	@OneToOne
	@JoinColumn(name = "deviceStatusNetworkCode")
	private DeviceStatusNetwork network;
	
	public DeviceStatus(LocalDateTime timestamp,
						Device device,
						DeviceStatusCpu cpu,
						DeviceStatusMemory memory,
						DeviceStatusDisk disk,
						DeviceStatusNetwork network) {
		this.timestamp = timestamp;
		this.device = device;
		this.cpu = cpu;
		this.memory = memory;
		this.disk = disk;
		this.network = network;
	}
}
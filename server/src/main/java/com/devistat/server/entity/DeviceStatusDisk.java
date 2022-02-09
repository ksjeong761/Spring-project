package com.devistat.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class DeviceStatusDisk {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusDiskCode;
	
	@OneToOne
	@JoinColumn(name = "deviceStatusCode")
	private DeviceStatus deviceStatus;
	
	private long readCount;
	private long readBytes;
	private long readTime;
	
	private long writeCount;
	private long writeBytes;
	private long writeTime;
	
    public DeviceStatusDisk(
    		long readCount,
    		long readBytes,
    		long readTime,
    		long writeCount,
    		long writeBytes,
    		long writeTime) {
    	this.readCount = readCount;
    	this.readBytes = readBytes;
    	this.readTime = readTime;
    	this.writeCount = writeCount;
    	this.writeBytes = writeBytes;
    	this.writeTime = writeTime;
    }
    
    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	stringBuilder.append("readCount : " + this.readCount + "\n");
    	stringBuilder.append("readBytes : " + this.readBytes + "\n");
    	stringBuilder.append("readTime : " + this.readTime + "\n");
    	stringBuilder.append("writeCount : " + this.writeCount + "\n");
    	stringBuilder.append("writeBytes : " + this.writeBytes + "\n");
    	stringBuilder.append("writeTime : " + this.writeTime + "\n");
    	
    	return stringBuilder.toString();
    }
}

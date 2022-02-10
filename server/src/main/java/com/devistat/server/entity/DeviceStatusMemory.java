package com.devistat.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class DeviceStatusMemory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer statusMemoryCode;
	
	@OneToOne
	@JoinColumn(name = "deviceStatusCode")
	@JsonIgnore
	private DeviceStatus deviceStatus;
	
	private long capacityTotal;
	private long capacityAvailable;
    
    public DeviceStatusMemory(
    		DeviceStatus deviceStatus,
			long capacityTotal,
			long capacityAvailable) {
    	this.deviceStatus = deviceStatus;
    	this.capacityTotal = capacityTotal;
    	this.capacityAvailable = capacityAvailable;
    }
    
    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	stringBuilder.append("capacityTotal : " + this.capacityTotal + "\n");
    	stringBuilder.append("capacityAvailable : " + this.capacityAvailable + "\n");
    	
    	return stringBuilder.toString();
    }
}

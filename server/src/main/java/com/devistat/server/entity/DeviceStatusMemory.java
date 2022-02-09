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
public class DeviceStatusMemory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusMemoryCode;
	
	@OneToOne
	@JoinColumn(name = "deviceStatusCode")
	private DeviceStatus deviceStatus;
	
	private long capacityTotal;
	private long capacityAvailable;
    
    public DeviceStatusMemory(long capacityTotal,
    				long capacityAvailable) {
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

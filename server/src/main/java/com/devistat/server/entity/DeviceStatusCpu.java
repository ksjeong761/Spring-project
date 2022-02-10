package com.devistat.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class DeviceStatusCpu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer statusCpuCode;
	
	@OneToOne
	@JoinColumn(name = "deviceStatusCode")
	@JsonIgnore
	private DeviceStatus deviceStatus;
	
	private Double timePercentUser;
	private Double timePercentSystem;
	private Double timePercentIdle;
    
	private Long contextSwitchCount;
	private Long interruptCount;
	private Long systemCallCount;
    
    public DeviceStatusCpu(
    		DeviceStatus deviceStatus,
			Double timePercentUser,
			Double timePercentSystem,
			Double timePercentIdle,
    	    long contextSwitchCount,
    	    long interruptCount,
    	    long systemCallCount) {
    	this.deviceStatus = deviceStatus;
    	this.timePercentUser = timePercentUser;
    	this.timePercentSystem = timePercentSystem;
    	this.timePercentIdle = timePercentIdle;
    	this.contextSwitchCount = contextSwitchCount;
    	this.interruptCount = interruptCount;
    	this.systemCallCount = systemCallCount;
    }
    
    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	stringBuilder.append("timePercentUser : " + this.timePercentUser + "\n");
    	stringBuilder.append("timePercentSystem : " + this.timePercentSystem + "\n");
    	stringBuilder.append("timePercentIdle : " + this.timePercentIdle + "\n");
    	stringBuilder.append("contextSwitchCount : " + this.contextSwitchCount + "\n");
    	stringBuilder.append("interruptCount : " + this.interruptCount + "\n");
    	stringBuilder.append("systemCallCount : " + this.systemCallCount + "\n");
    	
    	return stringBuilder.toString();
    }
}

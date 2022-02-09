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
public class DeviceStatusNetwork {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusNetworkCode;

	@OneToOne
	@JoinColumn(name = "deviceStatusCode")
	private DeviceStatus deviceStatus;
	
	private long sentBytes;
	private long sentPacketCount;
	
	private long receivedBytes;
	private long receivedPacketCount;
	
	private long errorInCount;
	private long erorrOutCount;
	
	private long dropInCount;
	private long dropOutCount;

    public DeviceStatusNetwork(long sentBytes,
						long sentPacketCount,
						long receivedBytes,
						long receivedPacketCount,
						long errorInCount,
						long erorrOutCount,
						long dropInCount,
						long dropOutCount) {
    	this.sentBytes = sentBytes;
    	this.sentPacketCount = sentPacketCount;
    	this.receivedBytes = receivedBytes;
    	this.receivedPacketCount = receivedPacketCount;
    	this.errorInCount = errorInCount;
    	this.erorrOutCount = erorrOutCount;
    	this.dropInCount = dropInCount;
    	this.dropOutCount = dropOutCount;
    }
    
    @Override
    public String toString() {
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	stringBuilder.append("sentBytes : " + this.sentBytes + "\n");
    	stringBuilder.append("sentPacketCount : " + this.sentPacketCount + "\n");
    	stringBuilder.append("receivedBytes : " + this.receivedBytes + "\n");
    	stringBuilder.append("receivedPacketCount : " + this.receivedPacketCount + "\n");
    	stringBuilder.append("errorInCount : " + this.errorInCount + "\n");
    	stringBuilder.append("erorrOutCount : " + this.erorrOutCount + "\n");
    	stringBuilder.append("dropInCount : " + this.dropInCount + "\n");
    	stringBuilder.append("dropOutCount : " + this.dropOutCount + "\n");
    	
    	return stringBuilder.toString();
    }
}

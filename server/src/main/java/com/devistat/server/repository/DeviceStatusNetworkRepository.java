package com.devistat.server.repository;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.DeviceStatusNetwork;

@Repository
public class DeviceStatusNetworkRepository extends AbstractRepository<DeviceStatusNetwork>{
	public DeviceStatusNetworkRepository(){
		setClazz(DeviceStatusNetwork.class);
	}
}
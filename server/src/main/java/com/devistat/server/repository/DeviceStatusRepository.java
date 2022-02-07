package com.devistat.server.repository;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.DeviceStatus;

@Repository
public class DeviceStatusRepository extends AbstractRepository<DeviceStatus>{
	public DeviceStatusRepository(){
		setClazz(DeviceStatus.class);
	}
}
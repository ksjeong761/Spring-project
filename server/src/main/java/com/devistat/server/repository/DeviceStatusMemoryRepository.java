package com.devistat.server.repository;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.DeviceStatusMemory;

@Repository
public class DeviceStatusMemoryRepository extends AbstractRepository<DeviceStatusMemory>{
	public DeviceStatusMemoryRepository(){
		setClazz(DeviceStatusMemory.class);
	}
}
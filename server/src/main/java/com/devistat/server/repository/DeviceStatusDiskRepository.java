package com.devistat.server.repository;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.DeviceStatusDisk;

@Repository
public class DeviceStatusDiskRepository extends AbstractRepository<DeviceStatusDisk>{
	public DeviceStatusDiskRepository(){
		setClazz(DeviceStatusDisk.class);
	}
}
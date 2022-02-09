package com.devistat.server.repository;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.DeviceStatusService;

@Repository
public class DeviceStatusServiceRepository extends AbstractRepository<DeviceStatusService>{
	public DeviceStatusServiceRepository(){
		setClazz(DeviceStatusService.class);
	}
}
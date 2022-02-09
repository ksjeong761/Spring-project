package com.devistat.server.repository;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.DeviceStatusCpu;

@Repository
public class DeviceStatusCpuRepository extends AbstractRepository<DeviceStatusCpu>{
	public DeviceStatusCpuRepository(){
		setClazz(DeviceStatusCpu.class);
	}
}
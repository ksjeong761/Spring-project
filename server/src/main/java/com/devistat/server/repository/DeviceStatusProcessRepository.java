package com.devistat.server.repository;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.DeviceStatusProcess;

@Repository
public class DeviceStatusProcessRepository extends AbstractRepository<DeviceStatusProcess>{
	public DeviceStatusProcessRepository(){
		setClazz(DeviceStatusProcess.class);
	}
}
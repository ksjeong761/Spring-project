package com.devistat.server.repository;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.Device;

@Repository
public class DeviceRepository extends AbstractRepository<Device>{
	public DeviceRepository(){
		setClazz(Device.class);
	}
}
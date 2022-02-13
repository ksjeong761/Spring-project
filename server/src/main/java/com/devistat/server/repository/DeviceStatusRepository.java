package com.devistat.server.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.DeviceStatus;

@Repository
public class DeviceStatusRepository extends AbstractRepository<DeviceStatus>{
	public DeviceStatusRepository(){
		setClazz(DeviceStatus.class);
	}
	   
    public List<DeviceStatus> findByPeriod(LocalDateTime start, LocalDateTime end){
    	return entityManager.createQuery(
    			"from " + DeviceStatus.class.toString()
    			+ " where loggedTime >= " + start
    			+ " and loggedTime <= " + end)
    			.getResultList();
    }
}
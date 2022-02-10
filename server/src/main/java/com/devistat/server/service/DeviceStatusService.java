package com.devistat.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devistat.server.entity.DeviceStatus;
import com.devistat.server.repository.DeviceStatusRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DeviceStatusService {
	
	@Autowired
	private DeviceStatusRepository repository;
	
	public String findAll() {
		var findResult = repository.findAll();
		
		for(var v : findResult) {
			System.out.println(v);
		}
		
		return "read";
	}
	
	public String add(DeviceStatus deviceStatus) {
		System.out.println("<DeviceStatusService> [add()] 진입 확인");
		repository.create(deviceStatus);
		return "create";
	}
	
	public String update(DeviceStatus device) {
		repository.update(device);
		return "update";
	}
	
	public String delete(DeviceStatus device) {
		repository.delete(device);
		return "delete";
	}
}

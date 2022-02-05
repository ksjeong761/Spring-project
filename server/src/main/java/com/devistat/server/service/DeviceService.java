package com.devistat.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devistat.server.entity.Device;
import com.devistat.server.repository.DeviceRepository;

@Service
@Transactional
public class DeviceService {
	
	@Autowired
	private DeviceRepository repository;
	
	public String findAll() {
		repository.findAll();
		return "read";
	}
	
	public String add(Device device) {
		repository.create(device);
		return "create";
	}
	
	public String update(Device device) {
		repository.update(device);
		return "update";
	}
	
	public String delete(Device device) {
		repository.delete(device);
		return "delete";
	}
}

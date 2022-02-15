package com.devistat.server.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devistat.server.entity.DeviceStatus;
import com.devistat.server.repository.DeviceStatusRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
@Transactional
public class DeviceStatusService {
    private final Logger logger = LoggerFactory.getLogger(DeviceStatusService.class);
	
	@Autowired
	private DeviceStatusRepository repository;

	public String findByPeriod(LocalDateTime start, LocalDateTime end) throws JsonProcessingException {
		List<DeviceStatus> findResult = repository.findByPeriod(start, end);
		
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	    String json = mapper.writeValueAsString(findResult);
	    logger.info("result json : " + json);
		return json;
	}
	
	public String findAll() throws JsonProcessingException {
		List<DeviceStatus> findResult = repository.findAll();

		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	    String json = mapper.writeValueAsString(findResult);
		return json;
	}
	
	public String add(DeviceStatus deviceStatus) {
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

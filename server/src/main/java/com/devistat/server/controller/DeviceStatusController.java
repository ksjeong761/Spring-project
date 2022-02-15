package com.devistat.server.controller;

import org.springframework.web.bind.annotation.RestController;

import com.devistat.server.entity.Device;
import com.devistat.server.entity.DeviceStatus;
import com.devistat.server.entity.DeviceStatusCpu;
import com.devistat.server.entity.DeviceStatusDisk;
import com.devistat.server.entity.DeviceStatusMemory;
import com.devistat.server.entity.DeviceStatusNetwork;
import com.devistat.server.service.DeviceStatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class DeviceStatusController {

    private final Logger logger = LoggerFactory.getLogger(DeviceStatusController.class);

    private LocalDateTime secondToLocalDateTime(long timestamp) {
    	return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.of("+09:00")); 
    }
    
	@Autowired
	private DeviceStatusService service;

	@ResponseBody
	@GetMapping("/devices/statuses")
	public String findByPeriod(@RequestParam long start, @RequestParam long end) throws JsonProcessingException {
		logger.info("start : " + start);
		logger.info("end : " + end);
		
		return service.findByPeriod(secondToLocalDateTime(start), secondToLocalDateTime(end));
	}

	//패러미터 없이 받는 부분을 지웠으므로 디폴트 값 추가 필요
	//	@ResponseBody
	//	@GetMapping("/devices/statuses")
	//	private String findAll() throws JsonProcessingException {
	//		return service.findAll();
	//	}
	
	@ResponseBody
	@PostMapping(value="/devices/statuses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private String add(HttpEntity<String> httpEntity) throws JsonMappingException, JsonProcessingException {
		
		JsonMapper mapper = JsonMapper.builder().build();
	    JsonNode actualObj = mapper.readTree(httpEntity.getBody());
	    logger.info("actualObj : " + actualObj);
		
	    LocalDateTime loggedTime = secondToLocalDateTime(actualObj.at("/time/logged_time").longValue());
		Device device = new Device(1, "addDevice NAME", new ArrayList<DeviceStatus>());
	    DeviceStatus deviceStatus = new DeviceStatus(loggedTime, device);
	    
		DeviceStatusCpu cpu = new DeviceStatusCpu(
				deviceStatus,
				actualObj.at("/cpu/cpu_times_percent/user").doubleValue(),
				actualObj.at("/cpu/cpu_times_percent/system").doubleValue(),
				actualObj.at("/cpu/cpu_times_percent/idle").doubleValue(),
				actualObj.at("/cpu/cpu_stats/ctx_switches").longValue(),
				actualObj.at("/cpu/cpu_stats/interrupts").longValue(),
				actualObj.at("/cpu/cpu_stats/syscalls").longValue());
		logger.info("cpu : " + cpu);

		DeviceStatusMemory memory = new DeviceStatusMemory(
				deviceStatus,
				actualObj.at("/memory/virtual_memory/total").longValue(),
				actualObj.at("/memory/virtual_memory/available").longValue());
		logger.info("memory : " + memory);
		
		DeviceStatusDisk disk = new DeviceStatusDisk(
				deviceStatus,
				actualObj.at("/disk/disk_io_counters/read_count").longValue(),
				actualObj.at("/disk/disk_io_counters/read_bytes").longValue(),
				actualObj.at("/disk/disk_io_counters/read_time").longValue(),
				actualObj.at("/disk/disk_io_counters/write_count").longValue(),
				actualObj.at("/disk/disk_io_counters/write_bytes").longValue(),
				actualObj.at("/disk/disk_io_counters/write_time").longValue());
		logger.info("disk : " + disk);

		DeviceStatusNetwork network = new DeviceStatusNetwork(
				deviceStatus,
				actualObj.at("/network/net_io_counters/bytes_sent").longValue(),
				actualObj.at("/network/net_io_counters/bytes_recv").longValue(),
				actualObj.at("/network/net_io_counters/packets_sent").longValue(),
				actualObj.at("/network/net_io_counters/packets_recv").longValue(),
				actualObj.at("/network/net_io_counters/errin").longValue(),
				actualObj.at("/network/net_io_counters/errout").longValue(),
				actualObj.at("/network/net_io_counters/dropin").longValue(),
				actualObj.at("/network/net_io_counters/dropout").longValue());
		logger.info("network : " + network);
		
		deviceStatus.deviceStatusSetter(cpu, memory, disk, network);
		service.add(deviceStatus);
	    return "";
	}

	@ResponseBody
	@PutMapping("/devices/statuses")
	private String update() {
		DeviceStatus deviceStatus = null;
		return service.update(deviceStatus);
	}

	@ResponseBody
	@DeleteMapping("/devices/statuses")
	private String delete() {
		DeviceStatus deviceStatus = null;
		return service.delete(deviceStatus);
	}
}
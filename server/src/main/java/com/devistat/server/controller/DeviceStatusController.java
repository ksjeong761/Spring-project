package com.devistat.server.controller;

import org.springframework.web.bind.annotation.RestController;

import com.devistat.server.entity.Device;
import com.devistat.server.entity.DeviceStatus;
import com.devistat.server.entity.DeviceStatusCpu;
import com.devistat.server.entity.DeviceStatusDisk;
import com.devistat.server.entity.DeviceStatusMemory;
import com.devistat.server.entity.DeviceStatusNetwork;
import com.devistat.server.service.DeviceStatusService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class DeviceStatusController {
	
	@Autowired
	private DeviceStatusService service;

	@ResponseBody
	@GetMapping("/devices/statuses")
	public String find() {
		return service.findAll();
	}

	@ResponseBody
	@PostMapping(value="/devices/statuses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String add(HttpEntity<String> httpEntity) throws JsonMappingException, JsonProcessingException {
		
		JsonMapper mapper = JsonMapper.builder().build();
	    JsonNode actualObj = mapper.readTree(httpEntity.getBody());
		System.out.println("<DeviceStatusController> actualObj : " + actualObj);
		
		LocalDateTime timestamp = LocalDateTime.parse(actualObj.at("/time/timestamp").textValue(),
													  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		//System.out.println("<DeviceStatusController> timestamp : " + timestamp);
		
		Device device = new Device(123, "addDevice NAME", new ArrayList<DeviceStatus>());

	    DeviceStatus deviceStatus = new DeviceStatus(timestamp, device);
	    
		DeviceStatusCpu cpu = new DeviceStatusCpu(
				deviceStatus,
				actualObj.at("/cpu/cpu_times/user").doubleValue(),
				actualObj.at("/cpu/cpu_times/system").doubleValue(),
				actualObj.at("/cpu/cpu_times/idle").doubleValue(),
				actualObj.at("/cpu/cpu_stats/ctx_switches").longValue(),
				actualObj.at("/cpu/cpu_stats/interrupts").longValue(),
				actualObj.at("/cpu/cpu_stats/syscalls").longValue());
		//System.out.println("<DeviceStatusController> cpu : " + cpu);

		DeviceStatusMemory memory = new DeviceStatusMemory(
				deviceStatus,
				actualObj.at("/memory/virtual_memory/total").longValue(),
				actualObj.at("/memory/virtual_memory/available").longValue());
		//System.out.println("<DeviceStatusController> memory : " + memory);
		
		DeviceStatusDisk disk = new DeviceStatusDisk(
				deviceStatus,
				actualObj.at("/disk/disk_io_counters/read_count").longValue(),
				actualObj.at("/disk/disk_io_counters/read_bytes").longValue(),
				actualObj.at("/disk/disk_io_counters/read_time").longValue(),
				actualObj.at("/disk/disk_io_counters/write_count").longValue(),
				actualObj.at("/disk/disk_io_counters/write_bytes").longValue(),
				actualObj.at("/disk/disk_io_counters/write_time").longValue());
		System.out.println("<DeviceStatusController> disk : " + disk);

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
		System.out.println("<DeviceStatusController> network : " + network);
		
		deviceStatus.deviceStatusSetter(cpu, memory, disk, network);
		service.add(deviceStatus);
	    return "";
	}

	@ResponseBody
	@PutMapping("/devices/statuses")
	public String update() {
		DeviceStatus deviceStatus = null;
		return service.update(deviceStatus);
	}

	@ResponseBody
	@DeleteMapping("/devices/statuses")
	public String delete() {
		DeviceStatus deviceStatus = null;
		return service.delete(deviceStatus);
	}
}
package com.devistat.server.controller;

import org.springframework.web.bind.annotation.RestController;

import com.devistat.server.entity.DeviceStatus;
import com.devistat.server.service.DeviceStatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PostMapping("/devices/statuses")
	public String add(HttpEntity<String> httpEntity) throws JsonMappingException, JsonProcessingException {
		System.out.println("<DeviceStatusController> [add()] 진입 확인");
		String jsonBody = httpEntity.getBody();
		System.out.println("<DeviceStatusController> jsonbody : " + jsonBody);
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree(jsonBody);
		System.out.println("<DeviceStatusController> actualObj : " + actualObj);
	    
	    var iter = actualObj.fieldNames();
	    while(iter.hasNext()) {
	    	String jn = iter.next();
	    	System.out.println(jn);
	    }
	    
	    // When
	    JsonNode jsonNode1 = actualObj.at("/timeSpent/boot_time");
	    System.out.println(jsonNode1);
//		ObjectMapper mapper = new ObjectMapper();
//	    JsonNode jsonTree = mapper.readTree(jsonBody);
//	    
//	    JsonNode d1 = jsonTree.get("cpu");
//	    JsonNode d2 = d1.get("cpu_times");
//	    
//	    String s1 = d1.toPrettyString();
//	    String s2 = d2.toPrettyString();
//	    
//	    if(s1 != null)
//	    	System.out.println(s1);
//	    else
//	    	System.out.println("s1 is null");
//	    
//	    if(s2 != null)
//	    	System.out.println(s2);
//	    else
//	    	System.out.println("s2 is null");
	    
	    
	    String cpu = "";
	    String memory = "";
	    String disk = "";
	    String sensor = "";
	    String timeSpent = "";
	    String user = "";
	    
	    DeviceStatus deviceStatus = new DeviceStatus(cpu,
												    memory,
												    disk,
												    sensor,
												    timeSpent,
												    user);
		
		return "";
		//return service.add(deviceStatus);
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
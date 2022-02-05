package com.devistat.server.controller;

import org.springframework.web.bind.annotation.RestController;

import com.devistat.server.entity.Device;
import com.devistat.server.service.DeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class DeviceController {
	
	@Autowired
	private DeviceService service;

	@ResponseBody
	@GetMapping("/devices")
	public String find() {
		//return userService.findUser();
		Device device = new Device("addDevice NAME");
		return service.add(device);
	}

	@ResponseBody
	@PostMapping("/devices")
	public String add() {
		Device device = new Device("addDevice NAME");
		return service.add(device);
	}

	@ResponseBody
	@PutMapping("/devices")
	public String update() {
		Device device = new Device("updateDevice NAME");
		return service.update(device);
	}

	@ResponseBody
	@DeleteMapping("/devices")
	public String delete() {
		Device device = new Device("deleteDevice NAME");
		return service.delete(device);
	}
}
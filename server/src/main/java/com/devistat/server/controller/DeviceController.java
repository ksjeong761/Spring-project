package com.devistat.server.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class DeviceController {
	@GetMapping("/devices")
	public String read() {
		return "read";
	}
	@PostMapping("/devices")
	public String create() {
		return "create";
	}
	@PutMapping("/devices")
	public String update() {
		return "update";
	}
	@DeleteMapping("/devices")
	public String delete() {
		return "delete";
	}
}
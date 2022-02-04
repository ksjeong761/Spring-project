package com.devistat.server.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {
	@GetMapping("/users")
	public String read() {
		return "read";
	}
	@PostMapping("/users")
	public String create() {
		return "create";
	}
	@PutMapping("/users")
	public String update() {
		return "update";
	}
	@DeleteMapping("/users")
	public String delete() {
		return "delete";
	}
}

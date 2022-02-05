package com.devistat.server.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.devistat.server.entity.User;
import com.devistat.server.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;

	@ResponseBody
	@GetMapping("/users")
	public String findUser() {
		//return userService.findUser();
		User user = new User("addUser ID","addUser PW","addUser NAME");
		return service.add(user);
	}

	@ResponseBody
	@PostMapping("/users")
	public String addUser() {
		User user = new User("addUser ID","addUser PW","addUser NAME");
		return service.add(user);
	}

	@ResponseBody
	@PutMapping("/users")
	public String update() {
		User user = new User("updateUser ID","updateUser PW","updateUser NAME");
		return service.update(user);
	}

	@ResponseBody
	@DeleteMapping("/users")
	public String delete() {
		User user = new User("deleteUser ID","deleteUser PW","deleteUser NAME");
		return service.delete(user);
	}
}

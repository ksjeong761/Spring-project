package com.devistat.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devistat.server.entity.User;
import com.devistat.server.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public String findAll() {
		repository.findAll();
		return "read";
	}
	
	public String add(User user) {
		repository.create(user);
		return "create";
	}
	
	public String update(User user) {
		repository.update(user);
		return "update";
	}
	
	public String delete(User user) {
		repository.delete(user);
		return "delete";
	}
}

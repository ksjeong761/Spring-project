package com.devistat.server.repository;

import org.springframework.stereotype.Repository;

import com.devistat.server.entity.User;

@Repository
public class UserRepository extends AbstractRepository<User>{
	public UserRepository(){
		setClazz(User.class);
	}
}
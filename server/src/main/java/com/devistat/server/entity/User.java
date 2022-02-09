package com.devistat.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class User {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer userCode;
	 
	 private String userId;
	 
	 private String userPw;
	 
	 private String userName;
	 
	 public User(String userId, String userPw, String userName) {
		 this.userId = userId;
		 this.userPw = userPw;
		 this.userName = userName;
	 }
}
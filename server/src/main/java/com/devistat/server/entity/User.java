package com.devistat.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "user_code")
	 Integer userCode;
	 
	 @Column(name = "user_id")
	 String userId;
	 
	 @Column(name = "user_pw")
	 String userPw;
	 
	 @Column(name = "user_name")
	 String userName;
	 
	 public User(String userId, String userPw, String userName) {
		 this.userId = userId;
		 this.userPw = userPw;
		 this.userName = userName;
	 }
}
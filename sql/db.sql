DROP DATABASE IF EXISTS devistat;
CREATE DATABASE devistat;
USE devistat;

CREATE TABLE authorities (
	authority_code			INT				NOT NULL		AUTO_INCREMENT,
	authority_name			VARCHAR(20)		NOT NULL,
	
	PRIMARY KEY(authority_code)
);

CREATE TABLE users (
	user_code				INT 				NOT NULL		AUTO_INCREMENT,
	user_id					VARCHAR(20) 	NOT NULL,
	user_pw					VARCHAR(20) 	NOT NULL,
	user_name				VARCHAR(20)		NOT NULL,
	authority_code			INT				NOT NULL		DEFAULT(1),
	
	PRIMARY KEY (user_code),
	FOREIGN KEY (authority_code) REFERENCES authorities(authority_code)
);

CREATE TABLE devices (
	device_code				INT				NOT NULL		AUTO_INCREMENT,
	device_name				VARCHAR(50)		NOT NULL,
	
	PRIMARY KEY (device_code)
);


CREATE TABLE mapping_users_and_devices (
	user_code				INT				NOT NULL,
	device_code				INT				NOT NULL,
	
	FOREIGN KEY (user_code) REFERENCES users(user_code),
	FOREIGN KEY (device_code) REFERENCES devices(device_code)
);

CREATE TABLE status_cpu (
	device_code							INT				NOT NULL,
	status_timestamp					TIMESTAMP		NOT NULL,
	
   cpu_times_percent_user			DOUBLE			NOT NULL,
   cpu_times_percent_system		DOUBLE			NOT NULL,
   cpu_times_percent_idle			DOUBLE			NOT NULL,
   cpu_times_percent_interrupt	DOUBLE			NOT NULL,
   cpu_times_percent_dpc			DOUBLE			NOT NULL,
   
	PRIMARY KEY (device_code, status_timestamp)
);

CREATE TABLE status_memory (
	device_code				INT				NOT NULL,
	status_timestamp		TIMESTAMP		NOT NULL,
	
	memory_type				VARCHAR(50)		NOT NULL,
	memory_total			BIGINT			NOT NULL,
	memory_used				BIGINT			NOT NULL,
   
	PRIMARY KEY (device_code, status_timestamp)
);

CREATE TABLE status_disk (
	device_code				INT				NOT NULL,
	status_timestamp		TIMESTAMP		NOT NULL,
	
	disk_partition			VARCHAR(50)		NOT NULL,
	disk_total				BIGINT			NOT NULL,
	disk_used				BIGINT			NOT NULL,
   
	PRIMARY KEY (device_code, status_timestamp)
);

CREATE TABLE status_process (
	device_code				INT				NOT NULL,
	status_timestamp		TIMESTAMP		NOT NULL,
	
	process_pid				INT				NOT NULL,
	process_name			VARCHAR(50)		NOT NULL,
	process_user_name		VARCHAR(50)		NOT NULL,
   
	PRIMARY KEY (device_code, status_timestamp)
);

CREATE TABLE status_service (
	device_code				INT				NOT NULL,
	status_timestamp		TIMESTAMP		NOT NULL,
	
	service_pid				INT				NOT NULL,
	service_name			VARCHAR(50)		NOT NULL,
	service_status			INT				NOT NULL,
   
	PRIMARY KEY (device_code, status_timestamp)
);

INSERT INTO authorities (authority_name) VALUES ('normal'), ('manager'), ('guest');
INSERT INTO users (user_id, user_pw, user_name, authority_code) VALUES ('test123', 'hash', 'test_user', 3);
INSERT INTO devices (device_name) VALUES ('test_device');
INSERT INTO mapping_users_and_devices (user_code, device_code) VALUES (1,1);

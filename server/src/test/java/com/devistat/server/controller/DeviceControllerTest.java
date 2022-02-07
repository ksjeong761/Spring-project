package com.devistat.server.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.devistat.server.entity.Device;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class DeviceControllerTest {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void test() throws Exception{
		Device device = new Device("junit_test_device_NAME");
		String uri = "/devices";
		String content = objectMapper.writeValueAsString(device);
		System.out.println(content);
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(result -> {
					MockHttpServletResponse response = result.getResponse();
					System.out.println(response.getContentAsString());
				});
	}
}

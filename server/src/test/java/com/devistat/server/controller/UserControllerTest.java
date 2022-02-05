package com.devistat.server.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.devistat.server.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void test() throws Exception{
		User user = new User("junit_test_user_ID", "junit_test_PW", "junit test_NAME");
		String uri = "/users";
		String content = "";
		
		content = objectMapper.writeValueAsString(user);
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

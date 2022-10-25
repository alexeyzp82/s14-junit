package com.softserve.itacademy;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepository;
	
	@MockBean
	private UserService userService;
	
	
	@Test
	@Transactional
	public void getAllUsersTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.model().attributeExists("users"))
		.andExpect(MockMvcResultMatchers.model().attribute("users", userService.getAll()));
	}
	
	@Test
	@Transactional
	public void addUserTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
				.param("email", "test@email")
				.param("firstName", "Yur")
				.param("lastName", "Gur")
				.param("role_id", "2")
				.param("password", "lhhdshs"))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	
	@Test
	@Transactional
	public void deleteUserTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/users/5/delete"))
		.andExpect(MockMvcResultMatchers.status().is3xxRedirection());
		User actual = userRepository.getUserByEmail("testUser@gmail.com");
        Assertions.assertNull(actual);
	}
	
}

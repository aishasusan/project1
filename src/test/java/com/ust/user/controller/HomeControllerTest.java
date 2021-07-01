package com.ust.user.controller;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.google.gson.Gson;
import com.ust.user.entity.User;
import com.ust.user.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class HomeControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private UserService service;
	
	

	@InjectMocks
	private UserController controller;

	public static User getUser() {
		
		User user = new User();	
		user.setCreated_date("10/11/2012");
		user.setUpdate_date("10/01/2020");
		user.setRole_id(1);
		user.setId(1);
		return user;
		
	}
	
	
	@Test
	public void postUserTest() throws Exception {

		mock.perform(MockMvcRequestBuilders.post("/app/user/edit-user/2").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getUser()))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}


	@Test
	public void putUserTest() throws Exception {

		mock.perform(MockMvcRequestBuilders.put("/app/user/create-user").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getUser()))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}
	
	@Test
	public void getallUserTest() throws Exception {

		 List<User> user = null;
		when(service.getUsers()).thenReturn(user);
		mock.perform(MockMvcRequestBuilders.get("/app/user/alluser").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getUser()))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void getUserTest() throws Exception {

		mock.perform(MockMvcRequestBuilders.get("/app/user/retrieve-user/2").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getUser()))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}

	
	@Test
	public void deleteUserTest() throws Exception {

		mock.perform(MockMvcRequestBuilders.delete("/app/user/delete-user/4").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(getUser()))).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}
//	
}

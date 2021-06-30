package com.ust.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.user.entity.User;
import com.ust.user.exception.IncompleteDetailsException;
import com.ust.user.exception.UserExistsException;
import com.ust.user.exception.UserNotFoundException;
import com.ust.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
@RequestMapping(value = "/app/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/home")
	@ApiOperation(value = "welcome user")
	public String getMessage() {
		return "Spring Boot Rest for User OPerations";
	}

	@PutMapping("/create-user")
	@ApiOperation(value = "adding user")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws UserExistsException  {

		User savedUser = userService.saveUser(user);
		
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);

	}

	@GetMapping("/retrieve-user/{id}")
	@ApiOperation(value = "to get all user")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) throws UserNotFoundException {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}

	@DeleteMapping("delete-user/{id}")
	@ApiOperation(value = "delete user")
	public ResponseEntity<User> getUserAfterDeleting(@PathVariable("id") Integer id) throws UserNotFoundException {
		return new ResponseEntity<User>(userService.deleteByUserId(id),HttpStatus.OK);
	}
	
	@PostMapping("/edit-user/{id}")
	@ApiOperation(value = "updating user")
    public ResponseEntity<?> updateBlog(@PathVariable("id") @RequestBody Integer id) throws UserNotFoundException, IncompleteDetailsException{
       return new ResponseEntity<User>(userService.updateByUserId(id), HttpStatus.OK);
    }
	
	


}

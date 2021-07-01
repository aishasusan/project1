package com.ust.user.controller;



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
	public ResponseEntity<?> saveUser(@RequestBody User user) {

		try {
			return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
		} catch (UserExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping("/retrieve-user/{id}")
	@ApiOperation(value = "to get all user")
	public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("delete-user/{id}")
	@ApiOperation(value = "delete user")
	public ResponseEntity<?> getUserAfterDeleting(@PathVariable("id") Integer id) {
		
		try {
			if(userService.deleteByUserId(id)) {
				return new ResponseEntity<String>("user deleted", HttpStatus.OK);
			}
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("User not deleted", HttpStatus.OK);
	}

	@PostMapping("/edit-user/{id}")
	@ApiOperation(value = "updating user")
	public ResponseEntity<?> updateBlog(@PathVariable Integer id, @RequestBody User user) {
		try {
			return new ResponseEntity<User>(userService.updateByUserId(id,user), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

}

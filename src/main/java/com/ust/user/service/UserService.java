package com.ust.user.service;

import java.util.List;

import java.util.Optional;

import com.ust.user.entity.User;
import com.ust.user.exception.IncompleteDetailsException;
import com.ust.user.exception.UserExistsException;
import com.ust.user.exception.UserInvalid;
import com.ust.user.exception.UserNotFoundException;

public interface UserService {
	


	public User saveUser(User user) throws UserExistsException;

	public User getUserById(Integer id) throws UserNotFoundException;

	public User updateByUserId(Integer id) throws IncompleteDetailsException, UserNotFoundException ;

	public User deleteByUserId(Integer id)throws UserNotFoundException;

	List<User> getAllUser()throws UserInvalid;
}

package com.ust.user.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.user.entity.User;
import com.ust.user.exception.UserExistsException;
import com.ust.user.exception.UserInvalid;
import com.ust.user.exception.UserNotFoundException;
import com.ust.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}


	
	public List<User> getAllUser() throws UserInvalid {
		if (!(userRepo.exists(null))) {
			throw new UserInvalid("User Doesn't Exists!!!!!!!!!!");
		}
		return userRepo.findAll();

}


	public User getUserById(Integer id) throws UserNotFoundException {
		
		if (!(userRepo.findById(id).isPresent())) {
			throw new UserNotFoundException("User Doesn't Exists!!!!!!!!!!");
		}
		User user = null;
		user = userRepo.findById(id).get();
		return user;

	}

	
	public User deleteByUserId(Integer id) throws UserNotFoundException {
		User user = null;
		Optional optional = userRepo.findById(id);
		if (!(userRepo.findById(id).isPresent())) {
			throw new UserNotFoundException("User Doesn't Exists!!!!!!!!!!");
		}
		if (optional.isPresent()) {
			user = userRepo.findById(id).get();
			userRepo.deleteById(id);
		}
		return user;

	}


	public User saveUser(User user) throws UserExistsException {
		User savedUser = userRepo.save(user);
		return savedUser;
	}

//	public User updateByUserId(User user,Integer id) throws UserNotFoundException {
//		if (!(userRepo.findById(user.getId()).isPresent())) {
//			throw new UserNotFoundException("User Doesn't Exists!!!!!!!!!!");
//		}
//		User temp = null;
//		Optional optional = userRepo.findById(id);
//		if(optional.isPresent()) {
//		temp = userRepo.findById(user.getId()).get();
//		temp.setCreated_date(user.getCreated_date());
//		temp.setUpdate_date(user.getUpdate_date());
//		temp.setRole_id(user.getRole_id());
//		userRepo.save(temp);
//		return temp;
//	    }
//		return temp;
//}
	
	public User updateByUserId( Integer id) throws UserNotFoundException {

		User updatedUser = null;
		
		if (!(userRepo.findById(id).isPresent())) {
			throw new UserNotFoundException("User Doesn't Exists!!!!!!!!!!");
		}
		
		Optional optional = userRepo.findById(id);
		if (optional.isPresent()) {
			updatedUser = userRepo.findById(id).get();
			updatedUser.setRole_id(updatedUser.getRole_id());
			updatedUser.setCreated_date(updatedUser.getCreated_date());
			updatedUser.setUpdate_date(updatedUser.getUpdate_date());
			userRepo.save(updatedUser);
		}
		return updatedUser;
	}
}

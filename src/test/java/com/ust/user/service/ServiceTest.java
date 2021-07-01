package com.ust.user.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.ust.user.entity.User;
import com.ust.user.exception.UserExistsException;
import com.ust.user.exception.UserNotFoundException;
import com.ust.user.repository.UserRepository;
import com.ust.user.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
class ServiceTest {
	
	 @Autowired
	    private MockMvc mockMvc;


//	@Before(value = "")
//	public void initMocks() {
//		MockitoAnnotations.initMocks(this);
//	}
	
	@Mock
	private UserRepository userRepo;

	@InjectMocks
	private UserServiceImpl userService;
	private User user;
	private List<User> userList;
	private Optional optional;

	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		user = new User(1, 1, "2020-12-20", "2021-02-15");
		optional = Optional.of(user);
	}

	@AfterEach
	public void tearDown() {
		user = null;
	}

	@Test
	public void givenUserToSaveThenShouldReturnSavedUser() throws UserExistsException{
		when(userRepo.save(any())).thenReturn(user);
        assertEquals(user, userService.saveUser(user));
        verify(userRepo, times(1)).save(user);
	}

	@Test
	public void givenBlogToSaveThenShouldNotReturnSavedBlog() throws UserExistsException {
       when(userRepo.save(user)).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> userService.saveUser(user));
	}

	@Test
	public void givenUserIdThenShouldReturnRespectiveUser() throws UserNotFoundException {
		 when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
	        User retrievedUser = userService.getUserById(user.getId());
	        verify(userRepo, times(2)).findById(anyInt());
	}

	@Test
	void givenBlogIdToDeleteThenShouldReturnDeletedBlog() throws UserNotFoundException {
		when(userRepo.findById(user.getId())).thenReturn(optional);
		Boolean deletedBlog = userService.deleteByUserId(1);
		assertEquals(true, deletedBlog.booleanValue());
		verify(userRepo, times(1)).findById(user.getId());
		verify(userRepo, times(1)).deleteById(user.getId());
	}

	@Test
	void givenBlogIdToDeleteThenShouldNotReturnDeletedBlog() throws UserNotFoundException {
		when(userRepo.findById(user.getId())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () ->
                userService.deleteByUserId(1));
        verify(userRepo, times(1)).findById(user.getId());

	}
	

	@Test
    public void givenUserToUpdateThenShouldReturnUpdatedUser() throws UserNotFoundException {
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepo.save(user)).thenReturn(user);
        user.setId(1);
        user.setRole_id(1);
        user.setCreated_date("2020-12-12");
        user.setUpdate_date("2020-12-12");
        User user1 = userService.updateByUserId(user.getId(), user);
        assertEquals(user1.getId(), 1);
        assertEquals(user1.getRole_id(), 1);
        assertEquals(user1.getCreated_date(), "2020-12-12");
        assertEquals(user1.getUpdate_date(), "2020-12-12");
        verify(userRepo, times(1)).save(user);
        verify(userRepo, times(1)).findById(user.getId());
    }

    @Test
    public void givenBlogToUpdateThenShouldNotReturnUpdatedBlog() throws UserNotFoundException {
        when(userRepo.existsById(user.getId())).thenReturn(false);
        Assertions.assertThrows(UserNotFoundException.class, () ->
                userService.updateByUserId(1, user));
    }


}
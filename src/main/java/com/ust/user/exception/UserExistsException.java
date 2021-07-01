package com.ust.user.exception;

public class UserExistsException extends Exception {
	private static final long serialVersionUID = 1L;
	private static String message="User Exists Exception";
	public  UserExistsException() {
		super(message);
	}
	
}

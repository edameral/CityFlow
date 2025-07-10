package com.project.cityFlowV2Back.exceptions;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException() {
		super();	
	}
	
	public UserNotFoundException(String message) {
		super(message);	
	}
	
}
package com.socialmediademo.exceptionhandler;

public class UnableToAddUserException extends RuntimeException {

	public UnableToAddUserException(String message) {
		super(message);
	}

}

package com.nemesis.api.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExistsException(String msg, Throwable t) {
		super(msg, t);
	}

	public UsernameAlreadyExistsException(String msg) {
		super(msg);
	}
}

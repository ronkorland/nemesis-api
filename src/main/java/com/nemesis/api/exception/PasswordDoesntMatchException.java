package com.nemesis.api.exception;

public class PasswordDoesntMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PasswordDoesntMatchException(String msg, Throwable t) {
		super(msg, t);
	}

	public PasswordDoesntMatchException(String msg) {
		super(msg);
	}
}

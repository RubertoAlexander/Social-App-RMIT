package com.sept.rest.webservices.restfulwebservices.exceptions;

public class ConflictException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3695010172211207129L;

	public ConflictException (String message) {
		super(message);
	}
}

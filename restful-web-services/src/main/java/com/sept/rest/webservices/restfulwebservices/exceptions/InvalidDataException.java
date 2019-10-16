package com.sept.rest.webservices.restfulwebservices.exceptions;

public class InvalidDataException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -859800639634919382L;

	public InvalidDataException (String message) {
		super(message);
	}

}

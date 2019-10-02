package com.sept.rest.webservices.restfulwebservices.exceptions;

public class InsufficientFundsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3695010172211207129L;

	public InsufficientFundsException (String message) {
		super(message);
	}
}

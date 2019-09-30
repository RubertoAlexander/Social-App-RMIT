package com.sept.rest.webservices.restfulwebservices.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<Object> handleAllExceptions(Exception ex, HttpServletRequest req, WebRequest request) {
//		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//		ApiResponse error = new ApiResponse(500, httpStatus, ex.getLocalizedMessage(), req.getRequestURI());
//		ex.printStackTrace();
//		return new ResponseEntity<>(error, httpStatus);
//	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public final ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex, HttpServletRequest req, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ApiResponse error = new ApiResponse(404, httpStatus, ex.getMessage(), req.getRequestURI());
		return new ResponseEntity<>(error, httpStatus);
	}
	
	@ExceptionHandler(DataDuplicationException.class)
	public final ResponseEntity<Object> handleDataConflictException(DataDuplicationException ex, HttpServletRequest req, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		ApiResponse error = new ApiResponse(409, httpStatus, ex.getMessage(), req.getRequestURI());
		return new ResponseEntity<>(error, httpStatus);
	}
	
	@ExceptionHandler(NoContentExistException.class)
	public final ResponseEntity<Object> handleNoContentExistException(NoContentExistException ex, HttpServletRequest req, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.NO_CONTENT;
		ApiResponse error = new ApiResponse(204, httpStatus, ex.getMessage(), req.getRequestURI());
		return new ResponseEntity<>(error, httpStatus);
	}
	
	@ExceptionHandler(ProductSoldException.class)
	public final ResponseEntity<Object> handleProductSoldException(ProductSoldException ex, HttpServletRequest req, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		ApiResponse error = new ApiResponse(409, httpStatus, ex.getMessage(), req.getRequestURI());
		return new ResponseEntity<>(error, httpStatus);
	}
	
	@ExceptionHandler(ConflictException.class) 
	public final ResponseEntity<Object> handleInsufficientException(ConflictException ex, HttpServletRequest req, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		ApiResponse error = new ApiResponse(409, httpStatus, ex.getMessage(), req.getRequestURI());
		return new ResponseEntity<>(error, httpStatus);
	}
	
	
}

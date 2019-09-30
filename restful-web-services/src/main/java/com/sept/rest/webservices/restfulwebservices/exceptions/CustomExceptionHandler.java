package com.sept.rest.webservices.restfulwebservices.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, HttpServletRequest req, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse error = new ErrorResponse(500, httpStatus, ex.getLocalizedMessage(), req.getRequestURI());
		ex.printStackTrace();
		return new ResponseEntity<>(error, httpStatus);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public final ResponseEntity<Object> handleDataNotFoundException(Exception ex, HttpServletRequest req,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ErrorResponse error = new ErrorResponse(404, httpStatus, ex.getMessage(), req.getRequestURI());
		return new ResponseEntity<>(error, httpStatus);
	}

	@ExceptionHandler({ DataDuplicationException.class, ProductSoldException.class, InsufficientFundsException.class })
	public final ResponseEntity<Object> handleDataConflictException(Exception ex, HttpServletRequest req,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		ErrorResponse error = new ErrorResponse(409, httpStatus, ex.getMessage(), req.getRequestURI());
		return new ResponseEntity<>(error, httpStatus);
	}
	
	@ExceptionHandler({SQLGrammarException.class, ConstraintViolationException.class, DataException.class})
	public final ResponseEntity<Object> handleSQLGrammerException(JDBCException ex, HttpServletRequest req,
			WebRequest request) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse error = new ErrorResponse(500, httpStatus, ex.getSQLState(), req.getRequestURI());
		return new ResponseEntity<>(error, httpStatus);
	}

}

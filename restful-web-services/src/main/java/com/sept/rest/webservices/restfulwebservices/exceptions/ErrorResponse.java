package com.sept.rest.webservices.restfulwebservices.exceptions;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement
public class ErrorResponse {
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	private int status;
	private HttpStatus error;
	private String message;
	private String path;
	
	public ErrorResponse() {
		
	}
	
	public ErrorResponse(int status, HttpStatus error, String message, String path) {
		super();
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public HttpStatus getError() {
		return error;
	}

	public void setError(HttpStatus error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}

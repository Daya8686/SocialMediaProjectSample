package com.socialmediademo.exceptionhandler;

import java.time.LocalDateTime;
import java.util.Map;

public class ExceptionDetails {
	
	private int status;
	
	private String message;
	
	private LocalDateTime exceptionTime;
	
	private Map<String, String> details;
	
	
	public ExceptionDetails() {
		super();
	}

	
	public ExceptionDetails(int status, String message, LocalDateTime exceptionTime, Map<String, String> details) {
		super();
		this.status = status;
		this.message = message;
		this.exceptionTime = exceptionTime;
		this.details = details;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(LocalDateTime exceptionTime) {
		this.exceptionTime = exceptionTime;
	}


	public Map<String, String> getDetails() {
		return details;
	}


	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	

	
	
	

}

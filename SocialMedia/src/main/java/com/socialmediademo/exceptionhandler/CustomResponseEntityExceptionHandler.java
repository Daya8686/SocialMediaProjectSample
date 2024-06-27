package com.socialmediademo.exceptionhandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler 
{

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		List<FieldError> fieldError = ex.getFieldErrors();
		Map<String, String> errorFileds = new HashMap<>();
		for (FieldError field : fieldError) {
			errorFileds.put(field.getField(), field.getDefaultMessage());
		}
		ExceptionDetails details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), "Validation Error",
				LocalDateTime.now(), errorFileds);

		return new ResponseEntity(details, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDetails> allExceptionHandler(Exception ex) {
		ExceptionDetails details = new ExceptionDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unknown exception occure: " + ex.getMessage(), LocalDateTime.now(), null);
		return new ResponseEntity<ExceptionDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionDetails> customUserNotFoundException(UserNotFoundException ex) {
		ExceptionDetails details = new ExceptionDetails(HttpStatus.NOT_FOUND.value(),
				 ex.getMessage(), LocalDateTime.now(), null);
		return new ResponseEntity<ExceptionDetails>(details, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnableToAddUserException.class)
	public ResponseEntity<ExceptionDetails> customUnableToAddUserException(UnableToAddUserException ex){
		ExceptionDetails details = new ExceptionDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				 ex.getMessage(), LocalDateTime.now(), null);
		return new ResponseEntity<ExceptionDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

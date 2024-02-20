package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ExceptionDefinition> userNotFound(StudentNotFoundException ex, WebRequest wb){
		
		return new ResponseEntity<ExceptionDefinition>(
			new ExceptionDefinition(
				LocalDateTime.now(),
				ex.getMessage(),
				wb.getDescription(false)
				),
			HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FacultyNotFoundException.class)
	public ResponseEntity<ExceptionDefinition> userNotFound(FacultyNotFoundException ex, WebRequest wb){
		
		return new ResponseEntity<ExceptionDefinition>(
			new ExceptionDefinition(
				LocalDateTime.now(),
				ex.getMessage(),
				wb.getDescription(false)
				),
			HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InstituteNotFoundException.class)
	public ResponseEntity<ExceptionDefinition> userNotFound(InstituteNotFoundException ex, WebRequest wb){
		
		return new ResponseEntity<ExceptionDefinition>(
			new ExceptionDefinition(
				LocalDateTime.now(),
				ex.getMessage(),
				wb.getDescription(false)
				),
			HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDefinition> globalException(Exception ex, WebRequest wb){
		
		return new ResponseEntity<ExceptionDefinition>(
			new ExceptionDefinition(
				LocalDateTime.now(),
				ex.getMessage(),
				wb.getDescription(false)
				),
				HttpStatus.NOT_FOUND);
	}
	
}

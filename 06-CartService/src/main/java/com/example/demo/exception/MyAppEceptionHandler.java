package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyAppEceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> excHandler(RuntimeException re){
		return new ResponseEntity<String>(re.getMessage(),HttpStatus.BAD_REQUEST);
	}

}

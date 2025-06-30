package com.example.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyAppExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public void excptionHandler(Exception e) {
		System.err.println(e.getMessage() +"\n"+e.getStackTrace());
	}

}

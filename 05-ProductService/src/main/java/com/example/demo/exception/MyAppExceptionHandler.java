package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.response.ErrResponse;

@RestControllerAdvice
public class MyAppExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrResponse> exHandler(Exception e){
		ErrResponse response = new ErrResponse();
		response.setMsg(e.getMessage());
		response.setStatuscode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrResponse>(response,HttpStatus.BAD_REQUEST);
	}

}

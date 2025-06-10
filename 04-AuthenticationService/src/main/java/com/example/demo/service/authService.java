package com.example.demo.service;

import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.User;

public interface authService {
	
	public User getUser(String email);
	public User saveUser(RegisterDto user);
	public String token(String email);
	public void validateToken(String token);
	
	
	

}

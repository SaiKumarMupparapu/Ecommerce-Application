package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.User;
import com.example.demo.service.authService;

@RefreshScope
@RestController
@RequestMapping("/auth")
public class AuthRestController {
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private authService service;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto register) {
		User existingUser = service.getUser(register.getEmail());
		if (existingUser == null) {
			User savedUser = service.saveUser(register);
			if (savedUser.getUserId() != null) {
				return new ResponseEntity<>("Registered", HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>("Account already exists", HttpStatus.CONFLICT);

	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody LoginDto login){
		ResponseDto response = new ResponseDto();
		try {
		Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		String token=null;
		if(authentication.isAuthenticated()) {
			
			token = service.token(login.getEmail());
			response.setToken(token);
			response.setIsLogged("yes");
		}
		}catch (Exception e) {
			response.setToken(null);
			response.setIsLogged("No");
		}
		
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
		
	}
     
	@GetMapping("/validate")
	public ResponseEntity<String> validateToken(@RequestParam("token") String jwtToken){
		String msg=null;
		try {
		service.validateToken(jwtToken);
		msg="valid token";

		}catch(Exception e){
				msg="not valid";		
		}
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}
}

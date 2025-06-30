package com.example.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

	@Autowired
	private HttpServletRequest request; 
	@Override
	public void apply(RequestTemplate template) {
		// TODO Auto-generated method stub
		
		String token = request.getHeader("Authorization");
		if(token!= null && token.startsWith("Bearer ")) {
			template.header("Authorization", token);
		}
		
	}

}

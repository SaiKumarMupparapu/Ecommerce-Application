package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Roles;
import com.example.demo.entity.User;
import com.example.demo.repo.RolesRepo;
import com.example.demo.repo.UserRepository;

@Service
public class AuthServiceImpl implements authService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RolesRepo roleRepo;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User saveUser(RegisterDto rDto) {
		User user = new User();
		user.setName(rDto.getName());
		user.setEmail(rDto.getEmail());
		user.setAddress(rDto.getAdrs());
		user.setPassword(encoder.encode(rDto.getPassword()));
		
		Set<Roles> userRoles = rDto.getRole().stream()
			    .map(roleName -> 
			        roleRepo.findByRoleName(roleName)
			            .orElseGet(() -> {
			                Roles role = new Roles();
			                role.setRoleName(roleName);
			                return roleRepo.save(role);
			            })
			    ) 
			    .collect(Collectors.toSet()); 

			user.setRoles(userRoles);
			return userRepo.save(user);

		
		
	}
	
	@Override
	public String token(String email) {
		List<String> roles = userRepo.findRoleNames(email);
		return jwtService.generateToken(email,roles);
	}
	@Override
	public void validateToken(String token) {
	   jwtService.validateToken(token);
	}

	@Override
	public User getUser(String email) {
		Optional<User> byEmail = userRepo.findByEmail(email);
		return byEmail.isEmpty()?null:byEmail.get();
	}
	
	
	

}

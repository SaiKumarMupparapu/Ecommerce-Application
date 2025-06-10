package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	 Optional<User> findByEmail(String email);
	 
	 @Query("SELECT r.roleName FROM User u JOIN u.roles r WHERE u.email = :email")
	 List<String>findRoleNames(String email);

}

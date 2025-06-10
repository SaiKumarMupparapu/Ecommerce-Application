package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {

	Optional<Roles> findByRoleName(String roleName);
//	@Query(value="select r.roleName from Users u "
//			+ "join UserRoles ur on u.userId=ur.userId "
//			+ "join Roles r on ur.roleId=r.roleId "
//			+ "where u.email=:username" , nativeQuery=true)
//	List<String> findRoleNames(String username);
}

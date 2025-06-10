package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Category;
import java.util.Optional;


public interface CategoryRepo extends JpaRepository<Category, Integer> {

	Optional<Category> findByCategoryName(String categoryName);
}

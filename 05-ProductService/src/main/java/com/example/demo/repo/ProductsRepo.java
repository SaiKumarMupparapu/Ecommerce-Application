package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Products;

public interface ProductsRepo extends JpaRepository<Products, Integer> {

}

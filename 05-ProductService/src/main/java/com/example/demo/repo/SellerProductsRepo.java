package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SellerProducts;

public interface SellerProductsRepo extends JpaRepository<SellerProducts, Integer> {

}

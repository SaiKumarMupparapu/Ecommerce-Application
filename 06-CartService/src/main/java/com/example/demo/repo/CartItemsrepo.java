package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CartItems;

public interface CartItemsrepo extends JpaRepository<CartItems, Integer> {

}

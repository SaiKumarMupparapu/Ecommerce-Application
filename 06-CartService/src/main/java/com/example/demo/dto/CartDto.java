package com.example.demo.dto;

import java.util.List;

public class CartDto {

	private Integer userId;
	private List<CartItemsDto> cartItems;


	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<CartItemsDto> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItemsDto> cartItems) {
		this.cartItems = cartItems;
	}




}

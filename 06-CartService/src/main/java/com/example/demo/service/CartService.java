package com.example.demo.service;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemsDto;

public interface CartService {

	public CartDto createCart(CartDto cartDto);
	public Boolean removeCart(Integer cartId);
	public CartItemsDto addItemToCart(Integer cartId,CartItemsDto item);
	public Boolean removeItemFromCart(Integer itemId);
	public CartItemsDto updateQuantaty(Integer itemId,CartItemsDto items);

}

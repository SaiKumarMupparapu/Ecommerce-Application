package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemsDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItems;

@Component
public class MyAppMapper {

	private final ModelMapper mapper;

	public MyAppMapper() {
		this.mapper = new ModelMapper();

		  mapper.addMappings(new PropertyMap<CartItems, CartItemsDto>() {
	            @Override
	            protected void configure() {
	                map().setCartId(source.getCart().getCartId());
	            }
	        });
	}

	public Cart convertToCart(CartDto cartDto) {
		Cart cart = mapper.map(cartDto, Cart.class);

		return cart;
	}

	public CartDto convertToCartDto(Cart cart) {
		return mapper.map(cart, CartDto.class);
	}

	public CartItems convertToCartItems(CartItemsDto cartItemsDto) {
		return mapper.map(cartItemsDto, CartItems.class);
	}

	public CartItemsDto convertToCartItemsDto(CartItems cartItems) {
		return mapper.map(cartItems, CartItemsDto.class);
	}

}

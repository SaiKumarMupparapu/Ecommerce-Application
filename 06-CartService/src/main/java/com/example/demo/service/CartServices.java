package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemsDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItems;
import com.example.demo.mapper.MyAppMapper;
import com.example.demo.repo.CartItemsrepo;
import com.example.demo.repo.CartRepo;
@Service
public class CartServices implements CartService {
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private CartItemsrepo itemsRepo;
	@Autowired
	private MyAppMapper mapper;

	@Override
	public CartDto createCart(CartDto cartDto) {
		Cart cart = mapper.convertToCart(cartDto);
		if(cart.getItems()!=null){
			for(CartItems items:cart.getItems()) {
				items.setCart(cart);
			}
		}

		Cart savedCart = cartRepo.save(cart);

		return mapper.convertToCartDto(savedCart);
	}

	@Override
	public Boolean removeCart(Integer cartId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(()->new RuntimeException("Cart not found"));
		cartRepo.delete(cart);
		return true;
	}

	@Override
	public CartItemsDto addItemToCart(Integer cartId,CartItemsDto item) {
		CartItems items = mapper.convertToCartItems(item);
		items.setCart(cartRepo.findById(cartId).orElseThrow(()->new RuntimeException("cart Not found")));

		return mapper.convertToCartItemsDto(itemsRepo.save(items));

	}

	@Override
	public Boolean removeItemFromCart(Integer itemId) {
		CartItems item = itemsRepo.findById(itemId).orElseThrow(() -> new RuntimeException("Item not Foumd"));
		itemsRepo.delete(item);
		return true;
	}

	@Override
	public CartItemsDto updateQuantaty(Integer itemId,CartItemsDto items) {
		CartItems item = itemsRepo.findById(itemId).orElseThrow(()->new RuntimeException("Items not Found"));
		item.setQuantity(items.getQuantity());
		return mapper.convertToCartItemsDto(itemsRepo.save(item));
	}

}

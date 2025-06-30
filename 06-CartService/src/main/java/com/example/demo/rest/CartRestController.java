package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemsDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.CartService;

@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("/api/cart")
public class CartRestController {

	@Autowired
	private CartService service;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse<CartDto>> createCart(@RequestBody CartDto cartDto) {
		CartDto cart = service.createCart(cartDto);
		ApiResponse<CartDto> response = new ApiResponse<>();
		response.setStatuscode(200);
		response.setMessage("Cart created");
		response.setData(cart);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/clear/{cartId}")
	public ResponseEntity<ApiResponse<String>> clearCart(@PathVariable Integer cartId) {

		 service.removeCart(cartId);
		ApiResponse<String> response = new ApiResponse<>();
		
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("Cart cleared");
	
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/add/item/{cartId}")
	public ResponseEntity<ApiResponse<String>> addItemToCart(@PathVariable("cartId") Integer cartId,@RequestBody CartItemsDto items) {
		CartItemsDto item = service.addItemToCart(cartId, items);
		System.out.println(item);
		ApiResponse<String> response=new ApiResponse<>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Item added ");
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}


	@DeleteMapping("/item/{id}")
	public ResponseEntity<ApiResponse<String>> removeItemFromCart(@PathVariable("id") Integer itemId) {
		 service.removeItemFromCart(itemId);
		ApiResponse<String> response = new ApiResponse<>();
			response.setStatuscode(HttpStatus.OK.value());
			response.setMessage("Cart cleared");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping(value="/qty/{id}",consumes = "application/json")
	public ResponseEntity<ApiResponse<CartItemsDto>> updateQunatity(@PathVariable("id") Integer itemId,@RequestBody CartItemsDto item) {

		CartItemsDto updatedItem = service.updateQuantaty(itemId, item);
		ApiResponse<CartItemsDto> response = new ApiResponse<>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Updated successfully");
		response.setData(updatedItem);

		return new ResponseEntity<>(response,HttpStatus.OK);
     }
}

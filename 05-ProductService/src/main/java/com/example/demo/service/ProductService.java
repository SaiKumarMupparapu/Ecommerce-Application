package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.entity.Products;
import com.example.demo.response.SellerProductDto;

public interface ProductService {
	
	public Products addProduct(CategoryDto categoryDto,ProductDto product,SellerProductDto sellerDto);
	public Products updateProduct(CategoryDto category, ProductDto product, SellerProductDto seller);
	public List<ProductResponseDto> getAllProducts();
	public Boolean deleteProduct(Integer sellerProductId);
	Page<ProductResponseDto> getAllProducts(int page, int size);


}

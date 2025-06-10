package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Products;
import com.example.demo.entity.SellerProducts;
import com.example.demo.response.SellerProductDto;
@Component
public class MyAppMapper {
	
private static final ModelMapper mapper=new ModelMapper();

    public ProductDto convertToProductDto(Products p) {
    	return mapper.map(p, ProductDto.class);
    }
    
    public Products convertToProduct(ProductDto p) {
    	return mapper.map(p, Products.class);
    }
    
    public CategoryDto convertToCategoryDto(Category c) {
    	return mapper.map(c, CategoryDto.class);
    }
    
    public Category convertToCategory(CategoryDto c) {
    	return mapper.map(c, Category.class);
    }
    
    public SellerProducts convertToSellerProduct(SellerProductDto s) {
    	return mapper.map(s, SellerProducts.class);
    }
    
    
}





















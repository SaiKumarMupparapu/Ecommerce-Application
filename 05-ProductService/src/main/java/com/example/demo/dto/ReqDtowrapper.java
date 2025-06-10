package com.example.demo.dto;

import com.example.demo.response.SellerProductDto;

public class ReqDtowrapper {
	
	private CategoryDto category;
	private ProductDto product;
	private SellerProductDto seller;
	
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public SellerProductDto getSeller() {
		return seller;
	}
	public void setSeller(SellerProductDto sellerProduct) {
		this.seller = sellerProduct;
	}
	
	

}

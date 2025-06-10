package com.example.demo.response;

public class SellerProductDto {
	
	private Integer sellerProductId;	
	private Integer sellerId;
	private Double price;
	private Integer stock;
	
	
	public Integer getSellerProductId() {
		return sellerProductId;
	}
	public void setSellerProductId(Integer sellerProductId) {
		this.sellerProductId = sellerProductId;
	}
	
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}

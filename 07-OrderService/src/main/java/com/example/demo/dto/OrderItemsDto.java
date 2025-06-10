package com.example.demo.dto;


public class OrderItemsDto {

    private Integer orderItemId;
    private Integer sellerProductId;
    private Integer quantity;
    private Double price;
    private Integer orderId;
	
	
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Integer getSellerProductId() {
		return sellerProductId;
	}
	public void setSellerProductId(Integer sellerProductId) {
		this.sellerProductId = sellerProductId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}

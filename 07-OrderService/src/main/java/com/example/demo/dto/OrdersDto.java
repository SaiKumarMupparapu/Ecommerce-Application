package com.example.demo.dto;

import java.util.List;

public class OrdersDto {

    private Integer orderId;
    private Integer buyerId;   
    private String paymentMethod;
    private Double totalAmount;
    private String deliveryAdrs;
    private String status;
    private List<OrderItemsDto> orderItems;
	
    
    
    
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getDeliveryAdrs() {
		return deliveryAdrs;
	}
	public void setDeliveryAdrs(String deliveryAdrs) {
		this.deliveryAdrs = deliveryAdrs;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OrderItemsDto> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemsDto> orderItems) {
		this.orderItems = orderItems;
	}
	

}

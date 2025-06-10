package com.pay.dto;

public class RazorpayOrderRequestDTO {
    private Integer orderId;
     // in INR
    private Double amount;
    private String currency;
    
    public RazorpayOrderRequestDTO() {
    	
    }
	public RazorpayOrderRequestDTO(Integer orderId, Double amount,String currency) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.currency=currency;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	} 
    
    
    
}


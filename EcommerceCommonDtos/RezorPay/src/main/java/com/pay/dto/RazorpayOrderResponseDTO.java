package com.pay.dto;

public class RazorpayOrderResponseDTO {
	
    private String razorpayOrderId;
    private String razorpayKey;
    private Double amount;
    
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public String getRazorpayKey() {
		return razorpayKey;
	}
	public void setRazorpayKey(String razorpayKey) {
		this.razorpayKey = razorpayKey;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
    
    
}


package com.example.demo.dto.common;

public class ProductSalesReportDTO {
	
    private Integer productId;
    private Long totalQuantity;
    private Double totalRevenue;
    private String status;
    
    public ProductSalesReportDTO() {
    }
    public ProductSalesReportDTO(Integer productId, Long totalQuantity, Double totalRevenue,String status) {
        this.productId = productId;
        this.totalQuantity = totalQuantity;
        this.totalRevenue = totalRevenue;
        this.status=status;
    }

    
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Long getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
	
    
}

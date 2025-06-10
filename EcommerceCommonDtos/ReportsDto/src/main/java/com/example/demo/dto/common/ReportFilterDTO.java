package com.example.demo.dto.common;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReportFilterDTO {
	@JsonFormat
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}

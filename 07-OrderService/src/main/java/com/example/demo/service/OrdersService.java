package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.OrdersDto;
import com.example.demo.dto.common.ProductSalesReportDTO;
import com.example.demo.entity.Orders;

public interface OrdersService {
	
	public Orders createOrder(OrdersDto orderDto);
	public Orders getOrderById(Integer orderId);
	public List<OrdersDto> getOrdersByBuyerId(Integer butyerId);
	//report
	public List<ProductSalesReportDTO> getSalesReport(LocalDate start, LocalDate end, String status);
	Orders getOrderByRazorpayOrderId(String razorpayOrderId);
	Orders save(Orders order);



}

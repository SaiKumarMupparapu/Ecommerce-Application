package com.example.demo.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrdersDto;
import com.example.demo.dto.common.ProductSalesReportDTO;
import com.example.demo.dto.common.ReportFilterDTO;
import com.example.demo.entity.Orders;
import com.example.demo.service.OrdersService;
import com.pay.dto.RazorpayCallbackDTO;

@RestController
@RequestMapping("/api/order")
public class OrdersController {
	@Autowired
	private OrdersService service;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/create")
	public ResponseEntity<Orders> createOrder(@RequestBody OrdersDto order){
		Orders placedOrder = service.createOrder(order);
        return new ResponseEntity<>(placedOrder,HttpStatus.CREATED);

	}
	

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/{orderId}")
	public ResponseEntity<Orders> getOrderById(@PathVariable("orderId") Integer id){
		Orders orders = service.getOrderById(id);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/orders/{buyerId}")
	public ResponseEntity<List<OrdersDto>> getOrderByBuyerId(@PathVariable("buyerId") Integer id){
		List<OrdersDto> orders = service.getOrdersByBuyerId(id);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
	@PostMapping("/report")
	public ResponseEntity<List<ProductSalesReportDTO>> getSalesReport(@RequestBody ReportFilterDTO filter) {
	    System.out.println(filter.getStartDate());
	    System.out.println(filter.getStartDate());
		List<ProductSalesReportDTO> report = service.getSalesReport(
	        filter.getStartDate(), filter.getEndDate(), filter.getStatus());
	   
	    return ResponseEntity.ok(report);
	}
	
	@PutMapping("/verify-payment")
	public ResponseEntity<String> verifyPayment(@RequestBody RazorpayCallbackDTO callback) {
	    Orders order = service.getOrderByRazorpayOrderId(callback.getRazorpayOrderId());
	    order.setStatus("SUCCESS"); // you can also check signature if needed
	    service.save(order);
	    return ResponseEntity.ok("Payment verified");
	}


	

}

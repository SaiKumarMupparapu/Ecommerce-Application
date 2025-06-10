package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.client.RazorpayFeignClient;
import com.example.demo.dto.OrdersDto;
import com.example.demo.dto.common.ProductSalesReportDTO;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.Orders;
import com.example.demo.mapper.MyAppMapper;
import com.example.demo.repo.OrderItemsRepo;
import com.example.demo.repo.OrdersRepo;
import com.pay.dto.RazorpayOrderRequestDTO;
import com.pay.dto.RazorpayOrderResponseDTO;

import jakarta.transaction.Transactional;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersRepo orderRepo;

	@Autowired
	private RazorpayFeignClient razorpayFeignClient;

	@Autowired
	private MyAppMapper mapper;

	@Autowired
	private OrderItemsRepo repo;

	@Override
	@Transactional
	public Orders createOrder(OrdersDto orderDto) {
		Orders orders = mapper.convertToOrder(orderDto);
		Double totalAmount = 0.0;
		if (orders.getOrderItems() != null) {
			for (OrderItems items : orders.getOrderItems()) {
				items.setOrders(orders);
				totalAmount = totalAmount + items.getPrice() * items.getQuantity();
			}
		}
		orders.setPlacedDate(LocalDateTime.now());
		orders.setStatus("PENDING");
		orders.setTotalAmount(totalAmount);
		Orders savedOrder = orderRepo.save(orders);

		RazorpayOrderRequestDTO requestDTO = new RazorpayOrderRequestDTO(savedOrder.getOrderId(),
				savedOrder.getTotalAmount(), "INR");

		ResponseEntity<RazorpayOrderResponseDTO> razorpayResponse = razorpayFeignClient.createPayment(requestDTO);
		String razorpayOrderId = razorpayResponse.getBody().getRazorpayOrderId();
		savedOrder.setRazorpayOrderId(razorpayOrderId);
		return orderRepo.save(savedOrder);

	}

	@Override
	public Orders getOrderById(Integer orderId) {
		return orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("no records found"));
	}

	@Override
	public List<OrdersDto> getOrdersByBuyerId(Integer buyerId) {
		List<Orders> orders = orderRepo.findByBuyerId(buyerId)
				.orElseThrow(() -> new RuntimeException("No records found"));
		if (orders.isEmpty()) {
			throw new RuntimeException("No records found");
		} else {
			List<OrdersDto> ordersDto = orders.stream().map(o -> mapper.convertToOrderDto(o))
					.collect(Collectors.toList());
			return ordersDto;
		}
	}

	@Override
	public List<ProductSalesReportDTO> getSalesReport(LocalDate start, LocalDate end, String status) {
		if (end == null)
			end = LocalDate.now();
		return repo.getProductSalesReport(start.atStartOfDay(), end.atTime(LocalTime.MAX), status);

	}

	@Override
	public Orders getOrderByRazorpayOrderId(String razorpayOrderId) {
		return orderRepo.findByRazorpayOrderId(razorpayOrderId)
				.orElseThrow(() -> new RuntimeException("Order not found"));
	}

	@Override
	public Orders save(Orders order) {
		return orderRepo.save(order);
	}

}

package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Orders;
import java.util.List;
import java.util.Date;



public interface OrdersRepo extends JpaRepository<Orders, Integer> {

	Optional<List<Orders>>findByBuyerId(Integer buyerId);
	List<Orders> findByStatus(String status);
	List<Orders> findByPlacedDate(Date placedDate);
	List<Orders> findByPlacedDateGreaterThanEqual(Date placedDate);
	Optional<Orders> findByRazorpayOrderId(String razorpayOrderId);
	

}

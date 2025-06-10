package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pay.dto.RazorpayCallbackDTO;

@FeignClient(name = "Order-service")
public interface OrdersFeignClient {
	
	@PutMapping("/api/order/verify-payment")
    ResponseEntity<String> verifyPayment(@RequestBody RazorpayCallbackDTO dto);

}

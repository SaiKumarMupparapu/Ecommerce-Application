package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pay.dto.RazorpayOrderRequestDTO;
import com.pay.dto.RazorpayOrderResponseDTO;

@FeignClient(name = "Payment-Service")
public interface RazorpayFeignClient {

    @PostMapping("/api/payment/create")
    ResponseEntity<RazorpayOrderResponseDTO> createPayment(@RequestBody RazorpayOrderRequestDTO dto);
}


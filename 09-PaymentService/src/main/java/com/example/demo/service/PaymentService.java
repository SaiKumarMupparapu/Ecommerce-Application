package com.example.demo.service;

import com.pay.dto.RazorpayOrderRequestDTO;
import com.pay.dto.RazorpayOrderResponseDTO;
import com.razorpay.RazorpayException;

public interface PaymentService {
    RazorpayOrderResponseDTO createRazorpayOrder(RazorpayOrderRequestDTO request) throws RazorpayException;
}


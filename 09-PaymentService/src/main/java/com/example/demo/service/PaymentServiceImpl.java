package com.example.demo.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pay.dto.RazorpayOrderRequestDTO;
import com.pay.dto.RazorpayOrderResponseDTO;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;



	@Override
	public RazorpayOrderResponseDTO createRazorpayOrder(RazorpayOrderRequestDTO request) throws RazorpayException {
		RazorpayClient client = new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject options = new JSONObject();
     // Razorpay requires paise
        options.put("amount", (int)(request.getAmount() * 100)); 
        options.put("currency", "INR");
        options.put("receipt", "order_rcptid_" + request.getOrderId());

        Order razorpayOrder = client.orders.create(options);

        RazorpayOrderResponseDTO response = new RazorpayOrderResponseDTO();
        response.setRazorpayOrderId(razorpayOrder.get("id"));
        response.setRazorpayKey(razorpayKey);
        response.setAmount(request.getAmount());

        return response;
	}
	
	

	
}

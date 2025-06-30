package com.example.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.OrdersFeignClient;
import com.pay.dto.RazorpayCallbackDTO;
import com.pay.dto.RazorpayOrderRequestDTO;
import com.pay.dto.RazorpayOrderResponseDTO;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Value("${razorpay.key}")
	private String key;
	@Value("${razorpay.secret}")
	private String secret;

	@Autowired
	private OrdersFeignClient ordersFeignClient; // for callback notification

	@PostMapping("/create")
	public ResponseEntity<RazorpayOrderResponseDTO> createPayment(@RequestBody RazorpayOrderRequestDTO dto) throws RazorpayException {
		RazorpayClient client = new com.razorpay.RazorpayClient(key, secret);

		JSONObject options = new JSONObject();
		options.put("amount", (int) (dto.getAmount() * 100)); // Razorpay uses paise
		options.put("currency", dto.getCurrency());
		options.put("receipt", "order_rcptid_" + dto.getOrderId());

		Order razorpayOrder = client.orders.create(options);
		RazorpayOrderResponseDTO response = new RazorpayOrderResponseDTO();
	    response.setRazorpayOrderId(razorpayOrder.get("id"));
	    response.setRazorpayKey(key);
	    response.setAmount(dto.getAmount());

	    return ResponseEntity.ok(response);
	}

	@PostMapping("/webhook")
	public ResponseEntity<String> handleWebhook(@RequestBody RazorpayCallbackDTO callback) {
		// Notify Order Service to mark order as paid
		ordersFeignClient.verifyPayment(callback);
		return ResponseEntity.ok("Payment verified and order updated");
	}
}

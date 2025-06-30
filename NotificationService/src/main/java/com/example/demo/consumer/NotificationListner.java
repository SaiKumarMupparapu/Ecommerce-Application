package com.example.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.constant.KafkaConstant;
import com.example.demo.dtos.AccountVerifiedEvent;
import com.example.demo.dtos.OrderStatusEvent;
import com.example.demo.dtos.OtpVerificationEvent;
import com.example.demo.dtos.PaymentStatusEvent;
import com.example.demo.email.EmailService;

@Component
public class NotificationListner {
	
	@Autowired
	private EmailService service;
	
	@KafkaListener(topics = KafkaConstant.Topic_EMAIL_OTP,groupId ="notification-group")
	public void otpVerification(OtpVerificationEvent otp) {
		service.sendOtpEmail(otp.getEmail(), otp.getOtp());
	}
	
	@KafkaListener(topics = KafkaConstant.Topic_EMAIL_VERIFIED,groupId ="notification-group")
	public void accountVerified(AccountVerifiedEvent account) {
		service.sendAccounrtVerifiedMail(account.getEmail(), account.getUsername());
	}
	
	@KafkaListener(topics = KafkaConstant.Topic_ORDER_STATUS,groupId ="notification-group")
	public void orderStatus(OrderStatusEvent order) {
		service.sendOrderStatusEmail(order.getEmail(), order.getOrderId(), order.getStatus());
	}
	@KafkaListener(topics =KafkaConstant.Topic_PAYMENT_STATUS,groupId = "notification-group")
	public void paymentStatusEvent(PaymentStatusEvent payment) {
		service.sendPaymentStatusMail(payment.getEmail(), payment.getPaymentId(), payment.getStatus());
	}
	

}

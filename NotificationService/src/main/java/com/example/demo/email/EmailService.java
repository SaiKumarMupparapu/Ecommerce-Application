package com.example.demo.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender sender;
	
	public void sendEmail(String to ,String subject, String body ) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		sender.send(message);
		
	}
	//sending otp by mail
	public void sendOtpEmail(String to , String otp) {
		sendEmail(to,"Otp Code","Your verification code : "+otp);
	}
	//sending account verification mail
	public void sendAccounrtVerifiedMail(String to , String username) {
		sendEmail( to,
				"Account verified",
				"Hi "+username +", your account has been successfully verified.");
	}
	// sending order status 
	public void sendOrderStatusEmail(String to,String orderId,String status) {
        sendEmail(to, "Order " + status.toUpperCase(), "Your order ID " + orderId + " has been " + status + ".");
	}
	//sending payment status 
	public void sendPaymentStatusMail(String to, String paymentId, String status) {
        sendEmail(to, "Payment " + status.toUpperCase(), "Your payment ID " + paymentId + " was " + status + ".");
    }

}

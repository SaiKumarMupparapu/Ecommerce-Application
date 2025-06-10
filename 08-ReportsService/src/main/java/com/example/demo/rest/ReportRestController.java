package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.common.ProductSalesReportDTO;
import com.example.demo.dto.common.ReportFilterDTO;
import com.example.demo.feign.OrdersFeignClinet;

@RestController
@RequestMapping("/reports/service")
public class ReportRestController {

	@Autowired
	private OrdersFeignClinet client;
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
	@PostMapping("/")
	public ResponseEntity<List<ProductSalesReportDTO>> getSalesReport(@RequestBody ReportFilterDTO filter){
		return client.getSalesReport(filter);
	}
}

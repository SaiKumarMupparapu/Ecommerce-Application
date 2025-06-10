package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.common.ProductSalesReportDTO;
import com.example.demo.dto.common.ReportFilterDTO;


@FeignClient(value = "order-service")
public interface OrdersFeignClinet {
	
	@PostMapping("/api/order/report")
	ResponseEntity<List<ProductSalesReportDTO>> getSalesReport(@RequestBody ReportFilterDTO filter);
   

}

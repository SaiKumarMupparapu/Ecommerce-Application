package com.example.demo.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.common.ProductSalesReportDTO;
import com.example.demo.entity.OrderItems;

public interface OrderItemsRepo extends JpaRepository<OrderItems, Integer> {

	@Query("SELECT new com.example.demo.dto.common.ProductSalesReportDTO(" +
		       "oi.sellerProductId, SUM(oi.quantity), SUM(oi.quantity * oi.price), o.status) " +
		       "FROM OrderItems oi " +
		       "JOIN oi.orders o " +
		       "WHERE (o.placedDate BETWEEN :startDate AND :endDate)"
		       + " AND (:status IS NULL OR status = :status )"
		       + "GROUP BY oi.sellerProductId, o.status")
		List<ProductSalesReportDTO> getProductSalesReport(
		        @Param("startDate") LocalDateTime startDate,
		        @Param("endDate") LocalDateTime endDate,
		        @Param("status") String status);

//	@Query("SELECT new com.example.demo.dto.common.ProductSalesReportDTO("
//			+ "oi.sellerProductId,SUM(oi.quantity),SUM(oi.quantity*oi.price),o.status) "
//			+ "FROM OrderItems oi "
//			+ "JOIN oi.orders o "
//			+ "WHERE  o.placedDate BETWEEN :start AND :end " 
//		    +   "GROUP BY oi.sellerProductId")
//	List<ProductSalesReportDTO> getProductSalesReport(
//			@Param("start") LocalDateTime start,
//			@Param("end") LocalDateTime end);

	
}

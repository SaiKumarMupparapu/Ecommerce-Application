package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductResponseDto;
import com.example.demo.dto.ReqDtowrapper;
import com.example.demo.entity.Products;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	private ProductService service;

	@PreAuthorize("hasRole('ROLE_SELLER')")
	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@RequestBody ReqDtowrapper req) {
		Products product = service.addProduct(req.getCategory(), req.getProduct(), req.getSeller());
		if (product.getProductId() != null) {
			return new ResponseEntity<String>("product saved", HttpStatus.CREATED);
		} else
			return new ResponseEntity<String>("product saved", HttpStatus.CONFLICT);

	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Products> updateProduct(@PathVariable("id") int pId,@RequestBody ReqDtowrapper req) {
		req.getProduct().setpId(pId);
		Products product = service.updateProduct(req.getCategory(), req.getProduct(), req.getSeller());
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@GetMapping("/all")
	public ResponseEntity<List<ProductResponseDto>> getPrds() {
		return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_SELLER','ROLE_ADMIN')")
	@DeleteMapping("/sellerPrd/{id}")
	public ResponseEntity<String> deletingProductBySeller(@PathVariable("id") Integer sellerProductId) {
		return service.deleteProduct(sellerProductId)
				? new ResponseEntity<>("Product deleted successfully", HttpStatus.OK)
				: new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@GetMapping("/all/page")
	public ResponseEntity<Page<ProductResponseDto>> getProductsAsPage(
			        @RequestParam(defaultValue = "0")  int page,
			        @RequestParam(defaultValue = "5") int size ){
		return new ResponseEntity<>(service.getAllProducts(page, size),HttpStatus.OK);
		
	}
}

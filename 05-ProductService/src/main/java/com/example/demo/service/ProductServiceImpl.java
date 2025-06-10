package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Products;
import com.example.demo.entity.SellerProducts;
import com.example.demo.mapper.MyAppMapper;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.ProductsRepo;
import com.example.demo.repo.SellerProductsRepo;
import com.example.demo.response.SellerProductDto;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ProductsRepo productRepo;
	@Autowired
	private SellerProductsRepo sellerRepo;
	@Autowired
	private MyAppMapper mapper;

	@Override
	public Products addProduct(CategoryDto categoryDto, ProductDto product, SellerProductDto sellerDto) {
		Optional<Category> byCatName = categoryRepo.findByCategoryName(categoryDto.getCategoryName());
		Products prd = mapper.convertToProduct(product);

		if (byCatName.isEmpty()) {
			prd.setCategory(categoryRepo.save(mapper.convertToCategory(categoryDto)));
		} else {
			prd.setCategory(byCatName.get());
		}
		SellerProducts sellerProduct = mapper.convertToSellerProduct(sellerDto);
		prd = productRepo.save(prd);
		sellerProduct.setProduct(prd);
		sellerRepo.save(sellerProduct);
		return prd;
	}

	@Transactional
	@Override
	public Products updateProduct(CategoryDto category, ProductDto product, SellerProductDto seller) {

		Products p = productRepo.findById(product.getpId()).orElseThrow(()-> new RuntimeException("Product Not found"));
	
		if(product.getTitle()!=null) p.setTitle(product.getTitle());
		if(product.getDescription()!=null) p.setDescription(product.getDescription());
		
		if (category != null && category.getCategoryName() != null) {
			Optional<Category> existCategory = categoryRepo.findByCategoryName(category.getCategoryName());

			Category caty = existCategory.orElseGet(() -> categoryRepo.save(mapper.convertToCategory(category)));
		   p.setCategory(caty);
		}
		Products updatedProduct = productRepo.save(p);
		
		if(seller.getSellerProductId()!=null) {
			Optional<SellerProducts> sellerProductsOptional = sellerRepo.findById(seller.getSellerProductId());
		if(sellerProductsOptional.isPresent()) {
			SellerProducts sellerProducts = sellerProductsOptional.get();
			if(seller.getPrice()!=null)sellerProducts.setPrice(seller.getPrice());
			if(seller.getStock()!=null)sellerProducts.setStock(seller.getStock());
		   sellerProducts.setProduct(updatedProduct);
			sellerRepo.save(sellerProducts);
		}
		
		}
		
		return updatedProduct;
		
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
	    List<SellerProducts> sellerProducts = sellerRepo.findAll();

	    return sellerProducts.stream().map(sp -> {
	        ProductResponseDto dto = new ProductResponseDto();
	        dto.setProductId(sp.getProduct().getProductId());
	        dto.setTitle(sp.getProduct().getTitle());
	        dto.setCategoryName(sp.getProduct().getCategory().getCategoryName());
	        dto.setPrice(sp.getPrice());
	        dto.setStock(sp.getStock());
	        return dto;
	    }).collect(Collectors.toList());
	}

	@Override
	public Boolean deleteProduct(Integer sellerProductId) {
		if(sellerRepo.existsById(sellerProductId)) {
			sellerRepo.deleteById(sellerProductId);
			return true;
		}		
		return false;
	}

	@Override
	public Page<ProductResponseDto> getAllProducts(int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<SellerProducts> sellerPage = sellerRepo.findAll(pageable);

	    return sellerPage.map(sp -> {
	        ProductResponseDto dto = new ProductResponseDto();
	        dto.setProductId(sp.getProduct().getProductId());
	        dto.setTitle(sp.getProduct().getTitle());
	        dto.setCategoryName(sp.getProduct().getCategory().getCategoryName());
	        dto.setPrice(sp.getPrice());
	        dto.setStock(sp.getStock());
	        return dto;
	    });
	}


}

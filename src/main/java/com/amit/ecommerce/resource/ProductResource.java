package com.amit.ecommerce.resource;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.ecommerce.entity.ProductEntity;
import com.amit.ecommerce.model.ProductObject;
import com.amit.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping(value="/api/product")
public class ProductResource {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping(value="/{productId}")
	public ProductEntity getProductById(@PathVariable String productId){
		ProductEntity entity = productRepository.findOne(productId);
				//.getOne(productId);
		if(entity == null){
			throw new ResourceNotFoundException("No product found", null);
		}
		return entity;
	}
	
	@GetMapping(value="/all")
	public Iterable<ProductEntity> getAll(){
		return productRepository.findAll(); 
	}
	@PostMapping(value="/add")
	public ProductEntity addProduct(@RequestBody final ProductObject product){
		ProductEntity productEnity = new ProductEntity();
		Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

		productEnity.setProductId(UUID.randomUUID().toString().replaceAll("-", "").trim());
		productEnity.setProductName(product.getProductName());
		productEnity.setProductDescription(product.getProductDescription());
		productEnity.setProductCategory(product.getProductCategory());
		productEnity.setCreatedDate(currentTimestamp);
		productEnity.setIsActive((short)1);
		productEnity.setLastUpdatedDate(currentTimestamp);
		
		productRepository.save(productEnity);
		return productRepository.findOne(productEnity.getProductId());
	}
}

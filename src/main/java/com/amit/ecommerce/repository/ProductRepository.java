package com.amit.ecommerce.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.amit.ecommerce.entity.ProductEntity;

//JpaRepository<ProductEntity, String>
public interface ProductRepository extends CrudRepository<ProductEntity, Serializable>{

}

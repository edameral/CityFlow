package com.project.cityFlowV2Back.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cityFlowV2Back.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByUserId(Long userId);
	
}
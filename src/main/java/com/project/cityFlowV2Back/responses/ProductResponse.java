package com.project.cityFlowV2Back.responses;

import java.time.LocalDateTime;

import com.project.cityFlowV2Back.entities.Product;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String productName;
    private String explanation;
    private LocalDateTime createdAt; 
    private String location;
    
    public ProductResponse(Product entity) {
    	this.id = entity.getId();
    	this.productName = entity.getProductName();
    	this.explanation = entity.getExplanation();
    	this.createdAt = entity.getCreatedAt();
    	this.location = entity.getLocation();
    }
    
}

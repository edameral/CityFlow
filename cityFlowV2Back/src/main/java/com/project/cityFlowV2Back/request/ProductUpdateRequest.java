package com.project.cityFlowV2Back.request;

import lombok.Data;

@Data
public class ProductUpdateRequest {
	Long id;
	
	String productName;
	
	String explanation;
	
	Long userId;
}

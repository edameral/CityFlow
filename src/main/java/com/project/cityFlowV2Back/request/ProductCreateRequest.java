package com.project.cityFlowV2Back.request;

import lombok.Data;

@Data
public class ProductCreateRequest {
	Long id;
	
	String productName;
	
	String explanation;
	
	Long userId;
		
	String location;
	
	String salesType;
}


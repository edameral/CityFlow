package com.project.cityFlowV2Back.responses;

import java.time.LocalDateTime;

import com.project.cityFlowV2Back.entities.User;

import lombok.Data;

@Data
public class UserResponse {
	Long id;
	String firebaseUid;
	LocalDateTime createdAt;
	
	public UserResponse(User entity) {
		this.id = entity.getId();
		this.firebaseUid = entity.getFirebaseUid();
		this.createdAt = entity.getCreatedAt();
	}
	
}

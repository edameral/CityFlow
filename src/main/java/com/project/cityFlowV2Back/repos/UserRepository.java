package com.project.cityFlowV2Back.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cityFlowV2Back.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByFirebaseUid(String firebaseUid);
}


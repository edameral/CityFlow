package com.project.cityFlowV2Back.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cityFlowV2Back.entities.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {
	
	Optional<Image> findFirstByProductId(Long productId);
	
	List<Image> findAllByProductId(Long productId);
	
}


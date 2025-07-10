package com.project.cityFlowV2Back.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cityFlowV2Back.entities.Image2;

public interface Image2Repository extends JpaRepository<Image2, Long>{
	List<Image2> findByProductId(Long productId);
	
	Image2 findFirstByProductId(Long productId);
}

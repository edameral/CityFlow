package com.project.cityFlowV2Back.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.cityFlowV2Back.entities.Image2;
import com.project.cityFlowV2Back.entities.Product;
import com.project.cityFlowV2Back.entities.User;
import com.project.cityFlowV2Back.repos.Image2Repository;
import com.project.cityFlowV2Back.repos.ProductRepository;
import com.project.cityFlowV2Back.repos.UserRepository;
import com.project.cityFlowV2Back.request.Image2CreateRequest;
import com.project.cityFlowV2Back.responses.Image2Response;

@Service
public class Image2Service {
    private final Image2Repository image2Repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Image2Service(Image2Repository image2Repository, UserRepository userRepository, ProductRepository productRepository) {
        this.image2Repository = image2Repository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Image2Response> getImagesByProductId(Long productId) {
        return image2Repository.findByProductId(productId)
            .stream().map(Image2Response::new).collect(Collectors.toList());
    }

    public Image2 createImage(Image2CreateRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Product product = productRepository.findById(request.getProductId()).orElse(null);
        if (user == null || product == null) return null;

        Image2 image = new Image2();
        image.setImageUrl(request.getImageUrl());
        image.setUser(user);
        image.setProduct(product);
        return image2Repository.save(image);
    }

    public void deleteImage(Long id) {
        image2Repository.deleteById(id);
    }
    
    public Image2 getFirstImageByProductId(Long productId) {
        return image2Repository.findFirstByProductId(productId);
    }
}

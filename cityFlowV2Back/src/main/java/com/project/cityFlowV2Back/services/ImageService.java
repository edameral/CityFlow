package com.project.cityFlowV2Back.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.cityFlowV2Back.entities.Image;
import com.project.cityFlowV2Back.entities.Product;
import com.project.cityFlowV2Back.entities.User;
import com.project.cityFlowV2Back.repos.ImageRepository;



@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private UserService userService;
    private ProductService productService;

    public ImageService(ImageRepository imageRepository,UserService userService,ProductService productService) {
        this.imageRepository = imageRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public void saveImage(MultipartFile file, Long userId, Long productId) {
    	User user = userService.getOneUserById(userId);
    	Product product = productService.getOneProductId(productId);
        try {
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setType(file.getContentType());
            image.setData(file.getBytes());
            image.setUser(user);
            image.setProduct(product);
            imageRepository.save(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Transactional(readOnly = true) // burada transaction açılır
    public Image getImageByProductId(Long productId) {
        return imageRepository.findFirstByProductId(productId)
            .orElseThrow(() -> new RuntimeException("Image not found for product id: " + productId));
    }
    
    @Transactional(readOnly = true)
    public List<Image> getAllImagesByProductId(Long productId) {
        return imageRepository.findAllByProductId(productId);
    }

}

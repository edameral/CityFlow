package com.project.cityFlowV2Back.controllers;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.cityFlowV2Back.entities.Image;
import com.project.cityFlowV2Back.services.ImageService;


@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public void uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("productId") Long productId) {
        imageService.saveImage(file, userId, productId);
    }
    
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<Resource> getImageByProductId(@PathVariable Long productId) {
        Image image = imageService.getImageByProductId(productId);
        ByteArrayResource resource = new ByteArrayResource(image.getData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, image.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(resource);
    }
    
    @GetMapping("/product/images/{productId}")
    public ResponseEntity<List<Image>> getAllImagesByProductId(@PathVariable Long productId) {
        List<Image> images = imageService.getAllImagesByProductId(productId);
        if (images.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(images);
    }
    
}

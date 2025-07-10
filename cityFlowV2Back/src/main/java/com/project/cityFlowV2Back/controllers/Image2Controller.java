package com.project.cityFlowV2Back.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.project.cityFlowV2Back.entities.Image2;
import com.project.cityFlowV2Back.request.Image2CreateRequest;
import com.project.cityFlowV2Back.responses.Image2Response;
import com.project.cityFlowV2Back.services.Image2Service;

@RestController
@RequestMapping("/images2")
public class Image2Controller {
    private final Image2Service image2Service;

    public Image2Controller(Image2Service image2Service) {
        this.image2Service = image2Service;
    }

    @GetMapping("/product/{productId}")
    public List<Image2Response> getImagesByProduct(@PathVariable Long productId) {
        return image2Service.getImagesByProductId(productId);
    }
    
    @GetMapping("/firstByProduct/{productId}")
    public Image2 getFirstImageByProductId(@PathVariable Long productId) {
        return image2Service.getFirstImageByProductId(productId);
    }

    @PostMapping
    public Image2 createImage(@RequestBody Image2CreateRequest request) {
        return image2Service.createImage(request);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        image2Service.deleteImage(id);
    }
}

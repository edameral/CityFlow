package com.project.cityFlowV2Back.request;

import lombok.Data;

@Data
public class Image2CreateRequest {
    private String imageUrl;
    private Long userId;
    private Long productId;
}
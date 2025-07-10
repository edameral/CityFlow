package com.project.cityFlowV2Back.responses;

import com.project.cityFlowV2Back.entities.Image2;
import lombok.Data;

@Data
public class Image2Response {
    private Long id;
    private String imageUrl;

    public Image2Response(Image2 entity) {
        this.id = entity.getId();
        this.imageUrl = entity.getImageUrl();
    }
}

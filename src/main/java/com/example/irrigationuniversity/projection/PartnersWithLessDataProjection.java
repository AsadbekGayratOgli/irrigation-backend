package com.example.irrigationuniversity.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface PartnersWithLessDataProjection {
    UUID getId();
    String getTitle();
    @Value("#{@contentImagesRepository.findImageIdByPartnerId(target.id)}")
    String getUrl();
}

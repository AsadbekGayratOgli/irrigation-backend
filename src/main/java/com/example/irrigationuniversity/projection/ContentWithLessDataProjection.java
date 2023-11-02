package com.example.irrigationuniversity.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ContentWithLessDataProjection {
    UUID getId();
    String getTitle();
    @Value("#{@contentImagesRepository.findImageIdByContentId(target.id)}")
    String getUrl();
}

package com.example.irrigationuniversity.projection;

import com.example.irrigationuniversity.entity.ContentImages;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ContentProjection {
    UUID getId();
    LocalDateTime getCreated_at();
    String getTitle();
    String getViews();
    @Value("#{@contentImagesRepository.findImageIdByContentId(target.id)}")
    String getUrl();
}

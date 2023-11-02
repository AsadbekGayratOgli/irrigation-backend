package com.example.irrigationuniversity.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface  ContentWithDataProjection {
    UUID getId();
    int getViews();
    String getBody();
    String getTitle();
    String getLan();
    LocalDateTime  getCreated_at();
    String getAuthor();
    @Value("#{@contentImagesRepository.findImagesIdByContentId(target.id)}")
    List<String> getUrl();
}

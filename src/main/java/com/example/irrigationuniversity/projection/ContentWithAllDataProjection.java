package com.example.irrigationuniversity.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ContentWithAllDataProjection {
    UUID getId();
    LocalDateTime getCreated_at();
    String getViews();
    String getAuthor();
    @Value("#{@contentImagesRepository.findImagesIdByContentId(target.id)}")
    List<String> getUrl();
    @Value("#{@contentDataRepository.findByContentId(target.id)}")
    List<ContentDataProjection> getContents();
}

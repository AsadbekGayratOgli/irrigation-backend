package com.example.irrigationuniversity.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PartnersWithAllDataProjection {
    UUID getId();
    LocalDateTime getCreated_at();
    String getViews();
    String getAuthor();
    @Value("#{@contentImagesRepository.findImagesIdByPartnerId(target.id)}")
    List<String> getUrl();
    @Value("#{@contentDataRepository.findByPartnerId(target.id)}")
    List<ContentDataProjection> getContents();
}

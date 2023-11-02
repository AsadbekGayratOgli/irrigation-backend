package com.example.irrigationuniversity.projection;

import com.example.irrigationuniversity.entity.ContentData;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface ContentDataProjection {
    UUID getId();
    @Value("#{@contentDataRepository.getContentDataByID(target.id)}")
    ContentData getCkeditor();

}

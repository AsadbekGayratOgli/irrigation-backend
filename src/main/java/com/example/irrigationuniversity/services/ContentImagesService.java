package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Content;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ContentImagesService {
    void deleteContentImages(UUID contentId);
    ResponseEntity<List<UUID>> saveContentSliderImages(UUID id, MultipartFile[] files) throws IOException;
    ResponseEntity<UUID> saveContentImages(MultipartFile[] files) throws IOException;

    String updateContentImages(Content content, List<String> contentImagesIds);

    void getImage(UUID contentId, HttpServletResponse httpServletResponse);
    void editImage(MultipartFile[] files) throws IOException;

    ResponseEntity<String>  deleteImage(UUID id);
}


package com.example.irrigationuniversity.controller;
import com.example.irrigationuniversity.services.ContentImagesService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/content_images")
public class ContentImagesController {
    private final ContentImagesService contentImagesService;
    public ContentImagesController(ContentImagesService fileService) {
        this.contentImagesService = fileService;
    }
    @GetMapping("/{id}")
    public void getImage(@PathVariable UUID id, HttpServletResponse httpServletResponse){
       contentImagesService.getImage(id,httpServletResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("/{id}")
    public ResponseEntity<List<UUID>> saveContentSliderImages(@PathVariable UUID id,
                                                              @RequestParam MultipartFile[] files) throws IOException {
        return contentImagesService.saveContentSliderImages(id,files);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<UUID> saveContentImages(@RequestParam MultipartFile[] files) throws IOException {
        return contentImagesService.saveContentImages(files);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable UUID id){
        return contentImagesService.deleteImage(id);
    }
}

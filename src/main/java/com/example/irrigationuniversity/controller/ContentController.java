package com.example.irrigationuniversity.controller;


import com.example.irrigationuniversity.payload.requestDTO.ContentDTO;
import com.example.irrigationuniversity.payload.responseDTO.ContentResponseDTO;
import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.projection.*;
import com.example.irrigationuniversity.services.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final ContentService contentService;
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("/categories1/{categories1Id}")
    public ResponseEntity<Content> saveNewCategory1Content(@PathVariable UUID categories1Id, @RequestBody ContentDTO contentDTO){
        return contentService.saveNewCategory1Content(categories1Id,contentDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("/categories2/{categories2Id}")
    public ResponseEntity<Content> saveNewCategory2Content(@PathVariable UUID categories2Id, @RequestBody ContentDTO contentDTO){
        return contentService.saveNewCategory2Content(categories2Id,contentDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("/categories3/{categories3Id}")
    public ResponseEntity<Content> saveNewCategory3Content(@PathVariable UUID categories3Id, @RequestBody ContentDTO contentDTO){
        return contentService.saveNewCategory3Content(categories3Id,contentDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("/categories4/{categories3Id}")
    public ResponseEntity<Content> saveNewCategory4Content(@PathVariable UUID categories3Id, @RequestBody ContentDTO contentDTO){
        return contentService.saveNewCategory3Content(categories3Id,contentDTO);
    }
    @GetMapping("/{lan}/{id}")
    public ResponseEntity<ContentWithDataProjection> getContentWithImagesAndTitleAndBody(@PathVariable String lan, @PathVariable UUID id){
        return  contentService.getContentWithContentData(id,lan.toUpperCase());
    }
    @GetMapping("/{lan}/{categoryId}/{pageNum}")
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories(@PathVariable String lan, @PathVariable UUID categoryId, @PathVariable int pageNum){
        return contentService.getGroupOfContents(categoryId,pageNum,lan.toUpperCase(),6);
    }
    @GetMapping("/categories1/{lan}/{categoryId}/{pageNum}")
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories1(@PathVariable String lan, @PathVariable UUID categoryId, @PathVariable int pageNum){
        return contentService.getGroupOfContentsOfCategories1(categoryId,pageNum,lan.toUpperCase(),6);
    }

    @GetMapping("/categories2/{lan}/{categoryId}/{pageNum}")
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories2(@PathVariable String lan, @PathVariable UUID categoryId, @PathVariable int pageNum){
        return contentService.getGroupOfContentsOfCategories2(categoryId,pageNum,lan.toUpperCase(),6);
    }
    @GetMapping("/categories3/{lan}/{categoryId}/{pageNum}")
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories3(@PathVariable String lan, @PathVariable UUID categoryId, @PathVariable int pageNum){
        return contentService.getGroupOfContentsOfCategories3(categoryId,pageNum,lan.toUpperCase(),6);
    }
    @GetMapping("/news/{lan}")
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfNews(@PathVariable String lan){
        UUID newsId = UUID.fromString("3383b09d-2e6a-4a37-b756-1aa10766b2c5");
        return contentService.getGroupOfContents(newsId,1,lan.toUpperCase(),7);
    }
    @GetMapping("/news2/{lan}/{id}")
    public ResponseEntity<List<ContentProjection>> getGroupOfContentsOfNews2(@PathVariable String lan, @PathVariable UUID id){
        UUID newsId = UUID.fromString("3383b09d-2e6a-4a37-b756-1aa10766b2c5");
        return contentService.getGroupOfNews2(newsId,id,lan.toUpperCase());
    }
    @GetMapping("/announcements/{lan}")
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfAnnouncements(@PathVariable String lan){
        UUID announcementsId = UUID.fromString("c3a98a08-9765-4925-9acd-f84e904ff600");
        return contentService.getGroupOfContents(announcementsId,1,lan.toUpperCase(),6);
    }


    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ContentWithAllDataProjection> getContentWithAllData(@PathVariable UUID id){
        return contentService.getContentWithAllData(id);
    }
    @GetMapping("/search/{lan}/{string}")
    public ResponseEntity<List<ContentSearchProjection>> searchContentByTitle(@PathVariable String lan,@PathVariable String string){
        return contentService.searchContentTitle(string,lan.toUpperCase());
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Content> editContent(@PathVariable UUID id,@RequestBody ContentDTO contentDTO){
        return contentService.editContent(id,contentDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContent(@PathVariable UUID id){
        return contentService.deleteContent(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<String> deleteAllContentsByCategory(@PathVariable UUID categoryId){
        return contentService.deleteAllContents(categoryId);
    }
}

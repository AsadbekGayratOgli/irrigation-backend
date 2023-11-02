package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.ContentDTO;
import com.example.irrigationuniversity.payload.responseDTO.ContentResponseDTO;
import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.projection.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ContentService {
    ResponseEntity<Content> saveNewCategory1Content(UUID category1Id, ContentDTO contentDTO);
    ResponseEntity<Content> saveNewCategory2Content(UUID category2Id, ContentDTO contentDTO);
    ResponseEntity<Content> saveNewCategory3Content(UUID category3Id, ContentDTO contentDTO);

    Content saveContent(ContentDTO contentDTO);

    ResponseEntity<String>  deleteContent(UUID contentId);
    ResponseEntity<String>  deleteAllContents(UUID categoryId);
    ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories1(UUID categoryId, Integer page, String lan, int limit);
    ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories2(UUID categoryId, Integer page, String lan, int limit);
    ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories3(UUID categoryId, Integer page, String lan, int limit);
    ResponseEntity<ContentWithDataProjection> getContentWithContentData(UUID id, String lan);
    ResponseEntity<ContentResponseDTO> getGroupOfContents(UUID foreignPartnersId, Integer pageNum, String lan, int limit);
    ResponseEntity<ContentWithAllDataProjection> getContentWithAllData(UUID id);
    ResponseEntity<Content> editContent(UUID id, ContentDTO contentDTO);
    ResponseEntity<List<ContentSearchProjection>> searchContentTitle(String contentTitlePart, String lan);
    ResponseEntity<List<ContentProjection>> getGroupOfNews2(UUID newsId, UUID id, String lan);

}

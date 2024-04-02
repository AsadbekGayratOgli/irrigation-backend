package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.ContentDataDTO;
import com.example.irrigationuniversity.payload.requestDTO.ContentDTO;
import com.example.irrigationuniversity.payload.responseDTO.ContentResponseDTO;
import com.example.irrigationuniversity.entity.*;
import com.example.irrigationuniversity.projection.*;
import com.example.irrigationuniversity.repository.*;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService{

    private final ContentRepository contentRepository;
    private final Categories_1Repository categories_1Repository;
    private final Categories_2Repository categories_2Repository;
    private final Categories_3Repository categories_3Repository;
    private final Categories_4Repository categories_4Repository;
    private final ContentDataRepository ckEditorRepository;
    private final ContentDataService ckEditorService;
    private final ContentImagesService contentImagesService;
    private final CommentService commentService;

    @Override
    public ResponseEntity<Content> saveNewCategory1Content(UUID category1Id, ContentDTO contentDTO) {
        Optional<Categories1> category1 = categories_1Repository.findById(category1Id);
        Content content = saveContent(contentDTO);
        category1.get().getContent().add(content);
        categories_1Repository.save(category1.get());
        return ResponseEntity.ok().body(content);
    }
    @Override
    public ResponseEntity<Content> saveNewCategory2Content(UUID category2Id, ContentDTO contentDTO){
        Optional<Categories2> category2 = categories_2Repository.findById(category2Id);
        Content content = saveContent(contentDTO);
        category2.get().getContent().add(content);
        categories_2Repository.save(category2.get());
        return ResponseEntity.ok().body(content);
    }
    @Override
    public ResponseEntity<Content> saveNewCategory3Content(UUID category3Id, ContentDTO contentDTO){
        Optional<Categories3> category3 = categories_3Repository.findById(category3Id);
        Content content = saveContent(contentDTO);
        category3.get().getContent().add(content);
        categories_3Repository.save(category3.get());
        return ResponseEntity.ok().body(content);
    }

    @Override
    public ResponseEntity<Content> saveNewCategory4Content(UUID category4Id, ContentDTO contentDTO){
        Optional<Categories4> category4 = categories_4Repository.findById(category4Id);
        Content content = saveContent(contentDTO);
        category4.get().getContent().add(content);
        categories_4Repository.save(category4.get());
        return ResponseEntity.ok().body(content);
    }
    
    @Override
    public Content saveContent(ContentDTO contentDTO){
        System.out.println(contentDTO);
        LocalDateTime now = LocalDateTime.now();
        Content newContent = contentRepository.save (new Content(UUID.randomUUID(), contentDTO.getAuthor(), now, 0));
        if(contentDTO.getUrls() != null) {
            contentImagesService.updateContentImages(newContent, contentDTO.getUrls());
        }
        for (ContentDataDTO ckEditorDTO : contentDTO.getContents()) {
          ckEditorService.saveContentData(newContent,ckEditorDTO.getCkEditor());
        }

        return newContent;
    }
    @Override
    public ResponseEntity<String>  deleteContent(UUID contentId){
        commentService.deleteAllComments(contentId);
        contentImagesService.deleteContentImages(contentId);
        ckEditorRepository.deleteByContentId(contentId);
        contentRepository.deleteFromCategories1_content(contentId);
        contentRepository.deleteFromCategories2_content(contentId);
        contentRepository.deleteFromCategories3_content(contentId);
        contentRepository.deleteFromCategories4_content(contentId);
        contentRepository.deleteById(contentId);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public ResponseEntity<String>  deleteAllContents(UUID categoryId){
        List<Content> allByCategoryId = contentRepository.findAllByCategoryId(categoryId);
        for (Content content : allByCategoryId) {
            deleteContent(content.getId());
        }
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories1(UUID categoryId, Integer page, String lan, int limit) {
        List<ContentProjection> groupOfContents = contentRepository.findGroupOfContentsOfCategories1ByLan(categoryId,page,lan,limit);
        return ResponseEntity.ok().body(new ContentResponseDTO(contentRepository.countByCategories1Id(categoryId),groupOfContents));
    }
    @Override
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories2(UUID categoryId, Integer page, String lan, int limit) {
        List<ContentProjection> groupOfContents = contentRepository.findGroupOfContentsOfCategories2ByLan(categoryId,page,lan,limit);
        return ResponseEntity.ok().body(new ContentResponseDTO(contentRepository.countByCategories2Id(categoryId),groupOfContents));
    }
    @Override
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories3(UUID categoryId, Integer page, String lan, int limit) {
        List<ContentProjection> groupOfContents = contentRepository.findGroupOfContentsOfCategories3ByLan(categoryId,page,lan,limit);
        return ResponseEntity.ok().body(new ContentResponseDTO(contentRepository.countByCategories3Id(categoryId),groupOfContents));
    }

    @Override
    public ResponseEntity<ContentResponseDTO> getGroupOfContentsOfCategories4(UUID categoryId, Integer page, String lan, int limit) {
        List<ContentProjection> groupOfContents = contentRepository.findGroupOfContentsOfCategories4ByLan(categoryId,page,lan,limit);
        return ResponseEntity.ok().body(new ContentResponseDTO(contentRepository.countByCategories4Id(categoryId),groupOfContents));
    }

    @Override
    public ResponseEntity<ContentWithDataProjection> getContentWithContentData(UUID id, String lan){
        contentRepository.updateViews(id);
        return ResponseEntity.ok().body( contentRepository.findByIdWithContentData(id,lan));
    }
    @Override
    public ResponseEntity<ContentResponseDTO> getGroupOfContents(UUID categoryId, Integer pageNum, String lan, int limit) {
        if (categories_1Repository.findById(categoryId).isPresent()){
            return getGroupOfContentsOfCategories1(categoryId,pageNum,lan,limit);
        } else if (categories_2Repository.findById(categoryId).isPresent()) {
            return getGroupOfContentsOfCategories2(categoryId,pageNum,lan,limit);
        } else if (categories_3Repository.findById(categoryId).isPresent()) {
            return getGroupOfContentsOfCategories3(categoryId,pageNum,lan,limit);
        }else if (categories_4Repository.findById(categoryId).isPresent()) {
            return getGroupOfContentsOfCategories4(categoryId,pageNum,lan,limit);
        }
        return ResponseEntity.notFound().build();
    }
    @Override
    public ResponseEntity<ContentWithAllDataProjection> getContentWithAllData(UUID id){
        return ResponseEntity.ok().body(contentRepository.findAllByIdWithAllData(id));
    }
    @Override
    public ResponseEntity<Content> editContent(UUID id, ContentDTO contentDTO) {
        System.out.println(contentDTO);
        Optional<Content> content = contentRepository.findById(id);
        for (String url : contentDTO.getDeletedUrls()) {
            contentImagesService.deleteImage(UUID.fromString(url));
        }
        if(contentDTO.getUrls() != null ) {
            contentImagesService.updateContentImages(content.get(), contentDTO.getUrls());
        }
        for (ContentDataDTO ckEditorDTO : contentDTO.getContents()) {
             ckEditorService.editContentData(ckEditorDTO,content.get());
        }
        return ResponseEntity.ok().body(content.get());
    }
    @Override
    public ResponseEntity<List<ContentSearchProjection>> searchContentTitle(String contentTitlePart, String lan) {
        return ResponseEntity.ok().body(contentRepository.searchContentByTitle(contentTitlePart,lan));
    }
    @Override
    public ResponseEntity<List<ContentProjection>> getGroupOfNews2(UUID newsId, UUID id, String lan) {
        return ResponseEntity.ok().body(contentRepository.findGroupOfNewsContents(newsId,id,lan));
    }


}

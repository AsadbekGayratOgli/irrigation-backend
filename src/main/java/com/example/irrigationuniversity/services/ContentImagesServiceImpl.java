package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.entity.ContentImages;
import com.example.irrigationuniversity.repository.ContentImagesRepository;
import com.example.irrigationuniversity.repository.ContentRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentImagesServiceImpl implements ContentImagesService {

    private final ContentImagesRepository contentImagesRepository;
    private final FileService fileService;
    private final ContentRepository contentRepository;
    @Override
    public void deleteContentImages(UUID contentId){
        for (ContentImages contentImages : contentImagesRepository.findByContentId(contentId)) {
            fileService.deleteFiles(contentImages.getUrl());
        }
        contentImagesRepository.deleteByContentId(contentId);
    }
    @Override
    public ResponseEntity<List<UUID>> saveContentSliderImages(UUID id, MultipartFile[] files) throws IOException {
        Optional<Content> content = contentRepository.findById(id);
        List<UUID> contentImages = new ArrayList<>();
        for (MultipartFile file : files) {
            contentImages.add(contentImagesRepository.save(new ContentImages(UUID.randomUUID(), fileService.saveFile("content", file), LocalDateTime.now(),true,content.get())).getId());
        }
        return ResponseEntity.ok().body(contentImages);
    }
    @Override
    public ResponseEntity<UUID> saveContentImages(MultipartFile[] files) throws IOException {
        UUID contentImage = null;
        for (MultipartFile file : files) {
             contentImage = (contentImagesRepository.save(new ContentImages(UUID.randomUUID(), fileService.saveFile("content", file), LocalDateTime.now(),false,null)).getId());
        }
        return ResponseEntity.ok().body(contentImage);
    }
    @Override
    public String updateContentImages(Content content, List<String> contentImagesIds){
        for (String contentImagesId : contentImagesIds) {
            Optional<ContentImages> contentImage = contentImagesRepository.findById(UUID.fromString(contentImagesId));
            contentImage.get().setContent(content);
            contentImagesRepository.save(contentImage.get());
        }
        return "Successfully updated";
    }
    @Override
    public void getImage(UUID id, HttpServletResponse httpServletResponse){
        fileService.getFile(contentImagesRepository.findById(id).get().getUrl(),httpServletResponse);

    }
    @Override
    public void editImage(MultipartFile[] files) throws IOException {
        saveContentImages(files);
    }
    @Override
    public ResponseEntity<String>  deleteImage(UUID id){
        fileService.deleteFiles(contentImagesRepository.findById(id).get().getUrl());
        contentImagesRepository.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted!");
    }


}

package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.ContentDataDTO;
import com.example.irrigationuniversity.entity.ContentData;
import com.example.irrigationuniversity.entity.Content;
import org.springframework.http.ResponseEntity;

public interface ContentDataService {

    ResponseEntity<String> saveContentData(Content content, ContentData ckEditor);

    void editContentData(ContentDataDTO ckEditorDTO, Content content);
}

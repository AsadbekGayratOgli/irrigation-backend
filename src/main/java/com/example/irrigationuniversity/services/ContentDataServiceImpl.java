package com.example.irrigationuniversity.services;
import com.example.irrigationuniversity.payload.requestDTO.ContentDataDTO;
import com.example.irrigationuniversity.entity.ContentData;
import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.repository.ContentDataRepository;
import com.example.irrigationuniversity.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentDataServiceImpl implements ContentDataService {
    private final ContentDataRepository contentDataRepository;
    private final ContentRepository contentRepository;
    @Override
    public ResponseEntity<String> saveContentData(Content content, ContentData ckEditor){
        ckEditor.setContent(content);
        contentDataRepository.save(ckEditor);
        return ResponseEntity.ok().body("Successfully saved!");
    }
    @Override
    public void editContentData(ContentDataDTO ckEditorDTO, Content content) {
        System.out.println(ckEditorDTO);
        ckEditorDTO.getCkEditor().setId(ckEditorDTO.getId());
        ckEditorDTO.getCkEditor().setContent(content);
        contentDataRepository.save(ckEditorDTO.getCkEditor());
    }

}

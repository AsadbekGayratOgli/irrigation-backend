package com.example.irrigationuniversity.payload.requestDTO;

import com.example.irrigationuniversity.entity.ContentData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDataDTO {
    private UUID id;
    private ContentData ckEditor ;
}

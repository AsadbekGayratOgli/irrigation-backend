package com.example.irrigationuniversity.payload.requestDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {
    private String author;
    private List<ContentDataDTO> contents;
    private List<String > urls;
    private List<String > deletedUrls;

}

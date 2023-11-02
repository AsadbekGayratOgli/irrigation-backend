package com.example.irrigationuniversity.payload.responseDTO;

import com.example.irrigationuniversity.projection.ContentProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentResponseDTO {
    private Integer pageCount;
    private List<ContentProjection> contents;
}

package com.example.irrigationuniversity.payload.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsArrayDTO {
    private List<CommentDTO> comments;
}

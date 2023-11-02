package com.example.irrigationuniversity.payload.requestDTO;

import com.example.irrigationuniversity.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private UUID id;
    private UUID contentId;
    private Comment comment;
}

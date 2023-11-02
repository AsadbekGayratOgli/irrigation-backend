package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.CommentsArrayDTO;
import com.example.irrigationuniversity.entity.Comment;
import com.example.irrigationuniversity.projection.CommentProjection;
import com.example.irrigationuniversity.projection.CommentProjectionAdminSide;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    ResponseEntity<String> deleteComment(UUID id);

    ResponseEntity<List<CommentProjectionAdminSide>> getCommentByContent(UUID contentId);

    ResponseEntity<List<CommentProjection>> getCommentByContentForClientside(UUID contentId);

    ResponseEntity<Comment> addNewComment(UUID contentId, Comment comment);

    ResponseEntity<String> deleteAllComments(UUID id);

    ResponseEntity<List<CommentProjectionAdminSide>> getAllComments();

    ResponseEntity<Comment> getComment(UUID id);

    ResponseEntity<List<CommentProjectionAdminSide>> editComments(CommentsArrayDTO commentsArrayDTO);
    ResponseEntity<Comment> editComment(UUID contentId, UUID id, Comment comment);
}

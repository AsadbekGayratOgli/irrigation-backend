package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.CommentDTO;
import com.example.irrigationuniversity.payload.requestDTO.CommentsArrayDTO;
import com.example.irrigationuniversity.entity.Comment;
import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.projection.CommentProjection;
import com.example.irrigationuniversity.projection.CommentProjectionAdminSide;
import com.example.irrigationuniversity.repository.CommentRepository;
import com.example.irrigationuniversity.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;
    @Override
    public ResponseEntity<String> deleteComment(UUID id){
        commentRepository.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public ResponseEntity<List<CommentProjection>> getCommentByContentForClientside(UUID contentId){
        return ResponseEntity.ok().body(commentRepository.findAllByContentForClientside(contentId));
    }
    @Override
    public ResponseEntity<Comment> addNewComment(UUID contentId, Comment comment){
        comment.setCreatedAt(LocalDateTime.now());
        Optional<Content> contents = contentRepository.findById(contentId);
        comment.setContent(contents.get());
        Comment save = commentRepository.save(comment);
        return  ResponseEntity.ok().body(save);
    }
    @Override
    public ResponseEntity<String> deleteAllComments(UUID contentId){
        commentRepository.deleteAllByContent(contentId);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public ResponseEntity<List<CommentProjectionAdminSide>> getCommentByContent(UUID contentId){
        return ResponseEntity.ok().body(commentRepository.findAllByContent(contentId));
    }
    @Override
    public ResponseEntity<List<CommentProjectionAdminSide>> getAllComments(){
        return ResponseEntity.ok().body(commentRepository.findAllByOrder());
    }
    @Override
    public ResponseEntity<Comment> getComment(UUID id){
        return ResponseEntity.ok().body(commentRepository.findById(id).get());
    }
    @Override
    public ResponseEntity<List<CommentProjectionAdminSide>> editComments(CommentsArrayDTO commentsArrayDTO){
        for (CommentDTO commentDTO : commentsArrayDTO.getComments()) {
            Optional<Content> content = contentRepository.findById(commentDTO.getContentId());
            commentDTO.getComment().setId(commentDTO.getId());
            commentDTO.getComment().setContent(content.get());
            commentRepository.save(commentDTO.getComment());
        }
        return ResponseEntity.ok().body(commentRepository.findAllByOrder());
    }
    @Override
    public ResponseEntity<Comment> editComment(UUID contentId, UUID id, Comment comment){
        comment.setId(id);
        Optional<Content> content = contentRepository.findById(contentId);
        comment.setContent(content.get());
        Comment savedComment = commentRepository.save(comment);
        return ResponseEntity.ok().body(commentRepository.findById(savedComment.getId()).get());
    }


}

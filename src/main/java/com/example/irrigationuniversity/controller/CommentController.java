package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.payload.requestDTO.CommentsArrayDTO;
import com.example.irrigationuniversity.entity.Comment;
import com.example.irrigationuniversity.projection.CommentProjection;
import com.example.irrigationuniversity.projection.CommentProjectionAdminSide;
import com.example.irrigationuniversity.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/{contentId}")
    public ResponseEntity<Comment> addNewComment(@PathVariable UUID contentId,@RequestBody Comment comment){
        return  commentService.addNewComment(contentId,comment);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<List<CommentProjectionAdminSide>> editComments(@RequestBody CommentsArrayDTO commentsArrayDTO){
        return  commentService.editComments(commentsArrayDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping ("/{contentId}/{id}")
    public ResponseEntity<Comment> editComment(@PathVariable UUID contentId,@PathVariable UUID id, @RequestBody Comment comment){
        return  commentService.editComment(contentId,id, comment);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CommentProjectionAdminSide>> getAllComments(){
        return commentService.getAllComments();
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/admin/{contentId}")
    public ResponseEntity<List<CommentProjectionAdminSide>> getComments(@PathVariable UUID contentId){
        return  commentService.getCommentByContent(contentId);
    }
    @GetMapping("/{contentId}")
    public ResponseEntity<List<CommentProjection>> getCommentsForClientside(@PathVariable UUID contentId){
        return  commentService.getCommentByContentForClientside(contentId);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/one/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable UUID id){
        return  commentService.getComment(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable UUID id){
        return commentService.deleteComment(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/all/{contentId}")
    public ResponseEntity<String> deleteAllComments(@PathVariable UUID contentId){
        return commentService.deleteAllComments(contentId);
    }
}

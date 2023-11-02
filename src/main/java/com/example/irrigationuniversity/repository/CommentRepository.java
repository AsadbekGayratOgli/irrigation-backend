package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Comment;
import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.projection.CommentProjection;
import com.example.irrigationuniversity.projection.CommentProjectionAdminSide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query(value = "select id,firstname,lastname,body,created_at,tel_or_email as telOrEmail,visible,content_id from comment where content_id = ?1 order by created_at desc",nativeQuery = true)
    List<CommentProjectionAdminSide> findAllByContent(UUID contentId);
    @Query(value = "select id,firstname,lastname,body,created_at from comment where content_id = ?1 and visible = true order by created_at desc",nativeQuery = true)
    List<CommentProjection> findAllByContentForClientside(UUID contentId);
    @Modifying
    @Transactional
    @Query(value = "delete from comment where content_id = ?1",nativeQuery = true)
    void deleteAllByContent(UUID contentId);
    @Query(value = "select id,firstname,lastname,body,created_at,tel_or_email as telOrEmail,visible,content_id from comment order by created_at desc",nativeQuery = true)
    List<CommentProjectionAdminSide> findAllByOrder();
}

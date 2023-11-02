package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.ContentImages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

public interface ContentImagesRepository extends JpaRepository<ContentImages, UUID> {

    @Query( value = "select * from content_images where content_id = ?1  ",nativeQuery = true)
    List<ContentImages> findByContentId(UUID contentId);
    @Query( value = "select '/content_images/'||id as url from content_images where content_id = ?1 and is_in_slider = true order by created_at desc ",nativeQuery = true)
    List<String> findImagesIdByContentId(UUID contentId);
    @Query( value = "select '/content_images/'||ci.id as url from content_images ci join partners p on p.content_id = ci.content_id where p.id = ?1 and is_in_slider = true order by created_at desc ",nativeQuery = true)
    List<String> findImagesIdByPartnerId(UUID partnerId);
    @Query( value = "select '/content_images/'||ci.id as url from content_images ci join partners p on ci.content_id = p.content_id where p.id = ?1 and is_in_slider = true order by created_at desc offset 1",nativeQuery = true)
    List<String> findImagesIdByPartnerIdWithOffset(UUID partnerId);
    @Query( value = "select '/content_images/'||id as url from content_images where content_id = ?1 and is_in_slider = true order by created_at desc  limit 1 ",nativeQuery = true)
    String findImageIdByContentId(UUID contentId);
    @Modifying
    @Transactional
    @Query( value = "delete from content_images  where content_id = ?1",nativeQuery = true)
    void deleteByContentId(UUID contentId);
    @Query( value = "select '/content_images/'||ci.id as url from content_images ci join partners p on ci.content_id = p.content_id where p.id = ?1 and is_in_slider = true order by created_at desc  limit 1 ",nativeQuery = true)
    String findImageIdByPartnerId(UUID partnerId);

}

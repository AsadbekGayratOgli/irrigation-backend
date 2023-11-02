package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID> {
    @Query(value = "select * from video ",nativeQuery = true)
    List<Video> findAllByGroup();
}

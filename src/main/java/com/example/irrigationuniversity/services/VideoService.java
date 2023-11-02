package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Video;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface VideoService {

    ResponseEntity<List<Video>> getVideos();

    ResponseEntity<Video> addVideo(Video video);

    ResponseEntity<String >deleteVideo(UUID id);

    ResponseEntity<Video> editVideo(UUID id, Video video);
}

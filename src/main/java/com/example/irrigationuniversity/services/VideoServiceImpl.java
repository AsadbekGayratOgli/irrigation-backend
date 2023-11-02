package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Video;
import com.example.irrigationuniversity.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    @Override
    public ResponseEntity<List<Video>> getVideos(){
        return ResponseEntity.ok().body(videoRepository.findAllByGroup());
    }
    @Override
    public ResponseEntity<Video> addVideo(Video video){
        return ResponseEntity.ok().body(videoRepository.save(video));
    }
    @Override
    public ResponseEntity<String >deleteVideo(UUID id){
        videoRepository.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public ResponseEntity<Video> editVideo(UUID id,Video video){
        video.setId(id);
       return ResponseEntity.ok().body(videoRepository.save(video));
    }
}
 
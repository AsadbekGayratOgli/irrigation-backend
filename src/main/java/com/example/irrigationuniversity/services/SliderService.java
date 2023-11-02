package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.SliderDTO;
import com.example.irrigationuniversity.projection.SliderWithAllDataProjection;
import com.example.irrigationuniversity.projection.SliderProjection;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface SliderService {

    ResponseEntity<UUID> saveSlide(SliderDTO sliderDTO);

    void saveSlideImage(UUID sliderId,MultipartFile file) throws IOException;

    ResponseEntity<String> deleteSlide(UUID id);
    ResponseEntity<List<SliderProjection>> getSlides(String lan);
    ResponseEntity<List<SliderWithAllDataProjection>> getSlideWithAllLan(UUID id);
    ResponseEntity<UUID> editSlide(UUID id, SliderDTO sliderDTO);


    void getSliderImage(UUID id, HttpServletResponse httpServletResponse);


    void editSlideImage(UUID sliderId, MultipartFile file) throws IOException;
}

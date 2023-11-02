package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.payload.requestDTO.SliderDTO;
import com.example.irrigationuniversity.projection.SliderWithAllDataProjection;
import com.example.irrigationuniversity.projection.SliderProjection;
import com.example.irrigationuniversity.services.SliderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/slider")
public class SliderController {
    private final SliderService sliderService;

    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<UUID> saveSlide(@RequestBody SliderDTO sliderDTO){
        return sliderService.saveSlide(sliderDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("/image/{sliderId}")
    public void saveSlideImage(@PathVariable UUID sliderId,@RequestParam MultipartFile file) throws IOException {
        System.out.println(file);
          sliderService.saveSlideImage(sliderId,file);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("/image/{sliderId}")
    public void editSlideImage(@PathVariable UUID sliderId,@RequestParam MultipartFile file) throws IOException {
        sliderService.editSlideImage(sliderId,file);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UUID> editSlide(@PathVariable UUID id, @RequestBody SliderDTO sliderDTO){
        return sliderService.editSlide(id,sliderDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String > deleteSlide(@PathVariable UUID id){
        return sliderService.deleteSlide(id);
    }
    @GetMapping("/{lan}")
    public ResponseEntity<List<SliderProjection>> getSlides(@PathVariable String lan){
        return sliderService.getSlides(lan.toUpperCase());
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/edit/{id}")
    public ResponseEntity<List<SliderWithAllDataProjection>> getSlideWithAllLan(@PathVariable UUID id){
        return sliderService.getSlideWithAllLan(id);
    }
    @GetMapping("/image/{id}")
    public void getSliderImage(@PathVariable UUID id, HttpServletResponse httpServletResponse){
        sliderService.getSliderImage(id,httpServletResponse);
    }
}

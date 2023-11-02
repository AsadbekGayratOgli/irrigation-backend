package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.SliderDTO;
import com.example.irrigationuniversity.entity.Slider;
import com.example.irrigationuniversity.entity.SliderTexts;
import com.example.irrigationuniversity.projection.SliderWithAllDataProjection;
import com.example.irrigationuniversity.projection.SliderProjection;
import com.example.irrigationuniversity.repository.SliderRepository;
import com.example.irrigationuniversity.repository.SliderTextRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SliderServiceImpl implements SliderService {

    private final SliderRepository sliderRepository;
   private final FileService fileService;
   private final SliderTextRepository sliderTextRepository;
    @Override
    public ResponseEntity<UUID> saveSlide(SliderDTO sliderDTO){
        Slider slider = sliderRepository.save(new Slider());
        sliderTextRepository.save(new SliderTexts(UUID.randomUUID(),sliderDTO.getTextUZ(),"UZ",slider));
        sliderTextRepository.save(new SliderTexts(UUID.randomUUID(),sliderDTO.getTextRU(),"RU",slider));
        sliderTextRepository.save(new SliderTexts(UUID.randomUUID(),sliderDTO.getTextEN(),"EN",slider));
        return ResponseEntity.ok().body(sliderRepository.findByIdAndLan(slider.getId()));
    }
    @Override
    public void saveSlideImage(UUID sliderId, MultipartFile file) throws IOException {
        Optional<Slider> slider = sliderRepository.findById(sliderId);
        slider.get().setUrl(fileService.saveFile("slider",file));
        sliderRepository.save(slider.get());
    }
    @Override
    public ResponseEntity<String> deleteSlide(UUID id){
        fileService.deleteFiles(sliderRepository.findById(id).get().getUrl());
        sliderTextRepository.deleteBySliderId(id);
        sliderRepository.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public ResponseEntity<List<SliderProjection>> getSlides(String lan){
        return ResponseEntity.ok().body(sliderRepository.findAllByLan(lan));
    }
    @Override
    public ResponseEntity<List<SliderWithAllDataProjection>> getSlideWithAllLan(UUID id){
        return ResponseEntity.ok().body(sliderRepository.findByIdWithAllLan(id));
    }
    @Override
    public ResponseEntity<UUID> editSlide(UUID id, SliderDTO sliderDTO){
        Optional<Slider> slider = sliderRepository.findById(id);
        sliderTextRepository.deleteBySliderId(id);
        sliderTextRepository.save(new SliderTexts(UUID.randomUUID(),sliderDTO.getTextUZ(),"UZ",slider.get()));
        sliderTextRepository.save(new SliderTexts(UUID.randomUUID(),sliderDTO.getTextRU(),"RU",slider.get()));
        sliderTextRepository.save(new SliderTexts(UUID.randomUUID(),sliderDTO.getTextEN(),"EN",slider.get()));
        return ResponseEntity.ok().body(sliderRepository.findByIdAndLan(id));
    }
    @Override
    public void editSlideImage(UUID id,MultipartFile file) throws IOException {
        Optional<Slider> slider = sliderRepository.findById(id);
        fileService.deleteFiles(slider.get().getUrl());
        slider.get().setUrl(fileService.saveFile("slider", file));
        sliderRepository.save(slider.get());
    }

    @Override
    public void getSliderImage(UUID id, HttpServletResponse httpServletResponse) {
        fileService.getFile(sliderRepository.findById(id).get().getUrl(),httpServletResponse);
    }

}

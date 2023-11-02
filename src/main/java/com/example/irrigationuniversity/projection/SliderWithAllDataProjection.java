package com.example.irrigationuniversity.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface SliderWithAllDataProjection {
    UUID getId();
    @Value("#{@sliderRepository.findSliderImage(target.id)}")
    String getUrl();
    @Value("#{@sliderRepository.findSliderText(target.id)}")
    List<SliderTextProjection> getTexts();


}
 
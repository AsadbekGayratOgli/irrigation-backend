package com.example.irrigationuniversity.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface SliderProjection {
    UUID getId();
    String getText();
    @Value("#{@sliderRepository.findSliderImage(target.id)}")
    String getUrl();

}

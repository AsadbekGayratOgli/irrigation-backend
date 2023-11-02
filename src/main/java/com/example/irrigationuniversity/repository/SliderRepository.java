package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Slider;
import com.example.irrigationuniversity.projection.SliderWithAllDataProjection;
import com.example.irrigationuniversity.projection.SliderProjection;
import com.example.irrigationuniversity.projection.SliderTextProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SliderRepository extends JpaRepository<Slider, UUID>{

    @Query(value = "select id from slider where id = ?1",nativeQuery = true)
    UUID findByIdAndLan( UUID id);
    @Query(value = "select s.id as id, st.text as text from slider s join slider_texts st on s.id = st.slider_id and st.lan = ?1",nativeQuery = true)
    List<SliderProjection> findAllByLan(String lan);
    @Query(value = "select 'slider/image/' || id from slider where id= ?1 and url is not null ",nativeQuery = true)
    String findSliderImage(UUID id);
    @Query(value = "select id from slider ",nativeQuery = true)
    List<SliderWithAllDataProjection> findByIdWithAllLan(UUID id);
    @Query(value = "select st.text as text, st.lan as lan from slider s join slider_texts st on s.id = st.slider_id and s.id = ?1",nativeQuery = true)
    List<SliderTextProjection> findSliderText(UUID id);
}

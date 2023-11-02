package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.SliderTexts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface SliderTextRepository extends JpaRepository<SliderTexts, UUID> {
    @Modifying
    @Transactional
    @Query(value = "delete from slider_texts where slider_id = ?1",nativeQuery = true)
    void deleteBySliderId(UUID id);
}

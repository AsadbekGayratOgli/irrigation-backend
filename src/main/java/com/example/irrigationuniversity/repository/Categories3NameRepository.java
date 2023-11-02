package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Categories3Names;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface Categories3NameRepository extends JpaRepository<Categories3Names, UUID> {
    @Modifying
    @Transactional
    @Query(value = "delete from categories3names where categories3_id = ?1",nativeQuery = true)
    void deleteByCategory3Id(UUID id);
    @Modifying
    @Transactional
    @Query(value = "delete from categories3names using categories3 c3  where c3.categories2_id = ?1",nativeQuery = true)
    void deleteByCategory2Id(UUID category2Id);
}

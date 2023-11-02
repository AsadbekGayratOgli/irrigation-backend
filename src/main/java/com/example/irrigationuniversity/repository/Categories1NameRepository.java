package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Categories1;
import com.example.irrigationuniversity.entity.Categories1Names;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface Categories1NameRepository extends JpaRepository<Categories1Names, UUID> {

    @Modifying
    @Transactional
    @Query(value = "delete from categories1names where categories1_id = ?1",nativeQuery = true)
    void deleteByCategory1Id(UUID id);
}

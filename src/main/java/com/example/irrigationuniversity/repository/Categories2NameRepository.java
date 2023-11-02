package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Categories2Names;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface Categories2NameRepository extends JpaRepository<Categories2Names, UUID> {
    @Modifying
    @Transactional
    @Query(value = "delete from categories2names where categories2_id = ?1",nativeQuery = true)
    void deleteByCategory2Id(UUID id);
    @Modifying
    @Transactional
    @Query(value = "delete from categories2names  using categories2 c2 where c2.categories1_id = ?1",nativeQuery = true)
    void deleteByCategory1Id(UUID category1Id);
}

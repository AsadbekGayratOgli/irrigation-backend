package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Categories3Names;
import com.example.irrigationuniversity.entity.Categories4Names;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface Categories4NameRepository extends JpaRepository<Categories4Names, UUID> {
    @Modifying
    @Transactional
    @Query(value = "delete from categories4names where categories4_id = ?1",nativeQuery = true)
    void deleteByCategory4Id(UUID id);

}

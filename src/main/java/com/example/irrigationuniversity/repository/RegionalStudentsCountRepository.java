package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.RegionalStudentsCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RegionalStudentsCountRepository extends JpaRepository<RegionalStudentsCount, UUID> {
    @Query(value = "select * from regional_students_count order by ord ",nativeQuery = true)
    List<RegionalStudentsCount> findAllByOrder();
}

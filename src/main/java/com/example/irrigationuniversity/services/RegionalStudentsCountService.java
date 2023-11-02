package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.RegionalStudentsCount;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface RegionalStudentsCountService {
    ResponseEntity<RegionalStudentsCount> changeStudentCount(UUID regionId, RegionalStudentsCount regionalAdministrations);

    ResponseEntity<List<RegionalStudentsCount>> getStudentCountByRegion();
}

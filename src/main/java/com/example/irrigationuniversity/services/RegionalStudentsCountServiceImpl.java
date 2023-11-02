package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.RegionalStudentsCount;
import com.example.irrigationuniversity.repository.RegionalStudentsCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegionalStudentsCountServiceImpl implements RegionalStudentsCountService {

    private final RegionalStudentsCountRepository regionalAdministrationRepository;
    @Override
    public ResponseEntity<RegionalStudentsCount> changeStudentCount(UUID regionId, RegionalStudentsCount regionalAdministrations){
        regionalAdministrations.setId(regionId);
        return ResponseEntity.ok().body(regionalAdministrationRepository.save(regionalAdministrations));
    }
    @Override
    public ResponseEntity<List<RegionalStudentsCount>> getStudentCountByRegion(){
        return ResponseEntity.ok().body(regionalAdministrationRepository.findAllByOrder());
    }
}

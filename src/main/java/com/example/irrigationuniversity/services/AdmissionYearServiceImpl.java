package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.AdmissionYear;
import com.example.irrigationuniversity.repository.AdmissionYearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdmissionYearServiceImpl implements AdmissionYearService {
    private final AdmissionYearRepository admissionYearRepository;
    @Override
    public ResponseEntity<AdmissionYear> getAdmissionYear(){
        return ResponseEntity.ok().body(admissionYearRepository.findAll().get(0));
    }
    @Override
    public ResponseEntity<AdmissionYear> saveAdmissionYear(AdmissionYear admissionYear){
        admissionYearRepository.deleteAll();
        return ResponseEntity.ok().body(admissionYearRepository.save(admissionYear));
    }
}

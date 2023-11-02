package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.AdmissionYear;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AdmissionYearService {
    ResponseEntity<AdmissionYear> getAdmissionYear();
    ResponseEntity<AdmissionYear> saveAdmissionYear(AdmissionYear admissionYear);
}

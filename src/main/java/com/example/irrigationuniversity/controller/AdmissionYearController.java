package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.entity.AdmissionYear;
import com.example.irrigationuniversity.services.AdmissionYearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/admission_year")
public class AdmissionYearController {
    private final AdmissionYearService admissionYearService;

    public AdmissionYearController(AdmissionYearService admissionYearService) {
        this.admissionYearService = admissionYearService;
    }
    @GetMapping
    public ResponseEntity<AdmissionYear> getAdmissionYear(){
        return admissionYearService.getAdmissionYear();
    }
    @PutMapping
    public ResponseEntity<AdmissionYear> saveAdmissionYear(@RequestBody AdmissionYear admissionYear){
        return admissionYearService.saveAdmissionYear(admissionYear);
    }
}

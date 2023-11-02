package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.entity.RegionalStudentsCount;
import com.example.irrigationuniversity.services.RegionalStudentsCountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/regional_students")
public class RegionalStudentsCountController {
    private final RegionalStudentsCountService regionalAdministrationService;

    public RegionalStudentsCountController(RegionalStudentsCountService regionalAdministrationService) {
        this.regionalAdministrationService = regionalAdministrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegionalStudentsCount>> getStudentCountByRegion(){
        return regionalAdministrationService.getStudentCountByRegion();
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("{regionId}")
    public ResponseEntity<RegionalStudentsCount> changeStudentCount(@PathVariable UUID regionId, @RequestBody RegionalStudentsCount regionalAdministrations){
        return regionalAdministrationService.changeStudentCount(regionId,regionalAdministrations);
    }
}

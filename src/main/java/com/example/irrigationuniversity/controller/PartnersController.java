package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.payload.requestDTO.ContentDTO;
import com.example.irrigationuniversity.projection.*;
import com.example.irrigationuniversity.services.PartnersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/foreign_partners")
public class PartnersController {
    private final PartnersService partnersService;
    public PartnersController(PartnersService partnersService) {
        this.partnersService = partnersService;
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<UUID> postForeignPartner(@RequestBody ContentDTO contentDTO){
        return partnersService.postForeignPartner(contentDTO);
    }
    @GetMapping("/{lan}")
    public ResponseEntity<List<PartnersWithLessDataProjection>> getForeignPartners(@PathVariable String lan){
        return partnersService.getForeignPartners(lan.toUpperCase());
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/edit/{id}")
    public ResponseEntity<PartnersWithAllDataProjection> getForeignPartnerWithAllData(@PathVariable UUID id){
        return partnersService.getForeignPartnerWithAllData(id);
    }
    @GetMapping("/{id}/{lan}")
    public ResponseEntity<PartnersWithDataWithoutLogoProjection> getForeignPartnerWithData(@PathVariable UUID id, @PathVariable String lan){
        return partnersService.getForeignPartnerWithData(id,lan.toUpperCase());
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Content> editForeignPartner(@PathVariable UUID id, @RequestBody ContentDTO contentDTO){
        return partnersService.editForeignPartner(id, contentDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteForeignPartner (@PathVariable UUID id){
        return partnersService.deleteForeignPartner(id);
    }

}

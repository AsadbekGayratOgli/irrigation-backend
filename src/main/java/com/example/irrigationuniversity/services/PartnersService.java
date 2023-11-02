package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.payload.requestDTO.ContentDTO;
import com.example.irrigationuniversity.projection.PartnersWithAllDataProjection;
import com.example.irrigationuniversity.projection.PartnersWithDataWithoutLogoProjection;
import com.example.irrigationuniversity.projection.PartnersWithLessDataProjection;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface PartnersService {
    ResponseEntity<UUID> postForeignPartner(ContentDTO contentDTO);

    ResponseEntity<List<PartnersWithLessDataProjection>> getForeignPartners(String lan);


    ResponseEntity<PartnersWithDataWithoutLogoProjection> getForeignPartnerWithData(UUID id, String lan);

    ResponseEntity<PartnersWithAllDataProjection> getForeignPartnerWithAllData(UUID id);


    ResponseEntity<Content> editForeignPartner(UUID partnerId, ContentDTO contentDTO);

    ResponseEntity<String> deleteForeignPartner(UUID id);
}

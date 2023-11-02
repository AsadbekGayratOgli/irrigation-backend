package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.entity.Partners;
import com.example.irrigationuniversity.payload.requestDTO.ContentDTO;
import com.example.irrigationuniversity.projection.PartnersWithDataWithoutLogoProjection;
import com.example.irrigationuniversity.projection.PartnersWithAllDataProjection;
import com.example.irrigationuniversity.projection.PartnersWithLessDataProjection;
import com.example.irrigationuniversity.repository.PartnersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartnersServiceImpl implements PartnersService{
    private final PartnersRepository partnersRepository;
    private final ContentService contentService;
    public PartnersServiceImpl(PartnersRepository partnersRepository, ContentService contentService) {
        this.partnersRepository = partnersRepository;
        this.contentService = contentService;
    }
    @Override
    public ResponseEntity<UUID> postForeignPartner(ContentDTO contentDTO){
        Content content = contentService.saveContent(contentDTO);
        partnersRepository.save(UUID.randomUUID(),content.getId());
        return ResponseEntity.ok().body(content.getId());
    }
    @Override
    public ResponseEntity<List<PartnersWithLessDataProjection>> getForeignPartners(String lan){
        return ResponseEntity.ok().body(partnersRepository.findAll(lan));
    }
    @Override
    public ResponseEntity<PartnersWithDataWithoutLogoProjection> getForeignPartnerWithData(UUID id, String lan){

        return ResponseEntity.ok().body(partnersRepository.findByIdWithContentDataWithoutLogo(id, lan));
    }
    @Override
    public ResponseEntity<PartnersWithAllDataProjection> getForeignPartnerWithAllData(UUID partnerId){
        return ResponseEntity.ok().body(partnersRepository.findAllByIdWithAllData(partnerId));
    }
    @Override
    public ResponseEntity<Content> editForeignPartner(UUID partnerId, ContentDTO contentDTO){
        Optional<Partners> partner = partnersRepository.findById(partnerId);
        return contentService.editContent(partner.get().getContent().getId(), contentDTO);
    }
    @Override
    public ResponseEntity<String> deleteForeignPartner(UUID partnerId){
        Optional<Partners> partner = partnersRepository.findById(partnerId);
        partnersRepository.deleteById(partnerId);
        return contentService.deleteContent(partner.get().getContent().getId());
    }



}

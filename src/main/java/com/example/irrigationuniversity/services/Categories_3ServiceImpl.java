package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.Category3NamesArrayDTO;
import com.example.irrigationuniversity.payload.requestDTO.Category3NamesDTO;
import com.example.irrigationuniversity.entity.Categories3;
import com.example.irrigationuniversity.projection.Categories3Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import com.example.irrigationuniversity.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class  Categories_3ServiceImpl implements Categories_3Service {
    private final Categories_2Repository categories_2Repository;
    private final Categories_3Repository categories_3Repository;
    private final Categories3NameRepository categories3NameRepository;
    private final Categories_4Service categories4Service;
    private final ContentService contentService;
    UUID foreignPartnersId = UUID.fromString("8744d530-5536-4734-a7f8-54d3543cdc5a");
    @Override
    public ResponseEntity<CategoriesProjection> addNewCategory(UUID categories2Id, Category3NamesArrayDTO category3NamesClass){
        Categories3 categories3 = new Categories3();
        categories3.setCreatedAt(LocalDateTime.now());
        categories3.setCategories2(categories_2Repository.findById(categories2Id).get());
        Categories3 save = categories_3Repository.save(categories3);
        for (Category3NamesDTO categories3NameDTO : category3NamesClass.getCategory3NamesDTO()) {
            categories3NameDTO.getCategories3Names().setCategories3(save);
            categories3NameRepository.save(categories3NameDTO.getCategories3Names());
        }
        return ResponseEntity.ok().body(categories_3Repository.findByIdAndLan(save.getId()));
    }
    @Override
    public ResponseEntity<String> deleteCategory(UUID id){
        contentService.deleteAllContents(id);
        categories4Service.deleteByCategories3(id);
        categories3NameRepository.deleteByCategory3Id(id);
        categories_3Repository.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public String deleteByCategories2(UUID category2Id){
        for (UUID uuid : categories_3Repository.findAllByCategories2(category2Id)) {
            contentService.deleteAllContents(uuid);
            categories4Service.deleteByCategories3(uuid);
            categories3NameRepository.deleteByCategory3Id(uuid);
        }
        categories_3Repository.deleteByCategories2(category2Id);
        return "Successfully deleted!";
    }
    @Override
    public ResponseEntity<CategoriesProjection> editCategory(UUID id, Category3NamesArrayDTO category3NamesArrayDTO){
        Optional<Categories3> categories3 = categories_3Repository.findById(id);
        for (Category3NamesDTO category3NameDTO : category3NamesArrayDTO.getCategory3NamesDTO()) {
            category3NameDTO.getCategories3Names().setId(category3NameDTO.getId());
            category3NameDTO.getCategories3Names().setCategories3(categories3.get());
            categories3NameRepository.save(category3NameDTO.getCategories3Names());
        }
        return ResponseEntity.ok().body(categories_3Repository.findByIdAndLan(id));
    }
    @Override
    public ResponseEntity<List<Categories3Projection>> getAllCategoriesByCategories2AndLan(String lan, UUID category2Id){
        return ResponseEntity.ok().body(categories_3Repository.findAllByCategories2AndLan(lan,category2Id));

    }
    @Override
    public ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(UUID id){
        return ResponseEntity.ok().body(categories_3Repository.findByIdWithAllNames(id));
    }
    @Override
    public ResponseEntity<UUID> getIdOfForeignPartners(){
        return ResponseEntity.ok().body(foreignPartnersId);
    }
    }

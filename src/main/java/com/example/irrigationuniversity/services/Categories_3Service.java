package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.Category3NamesArrayDTO;
import com.example.irrigationuniversity.projection.Categories3Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface Categories_3Service {

    ResponseEntity<CategoriesProjection> addNewCategory(UUID categories2Id, Category3NamesArrayDTO category3NamesClass);

    ResponseEntity<String> deleteCategory(UUID id);

    String deleteByCategories2(UUID category2Id);

    ResponseEntity<CategoriesProjection> editCategory(UUID id, Category3NamesArrayDTO category3NamesArrayDTO);

    ResponseEntity<List<Categories3Projection>> getAllCategoriesByCategories2AndLan(String lan, UUID category2Id);

    ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(UUID id);

    ResponseEntity<UUID> getIdOfForeignPartners();
}

package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.Category4NamesArrayDTO;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface Categories_4Service {

    ResponseEntity<CategoriesProjection> addNewCategory(UUID categories3Id, Category4NamesArrayDTO category4NamesClass);

    ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(UUID id);

    ResponseEntity<String> deleteCategory(UUID id);

    String deleteByCategories3(UUID category2Id);

    ResponseEntity<CategoriesProjection> editCategory(UUID id, Category4NamesArrayDTO category4NamesArrayDTO);
}

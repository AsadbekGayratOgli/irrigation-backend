package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.Category2NamesArrayDTO;
import com.example.irrigationuniversity.projection.Categories2Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface Categories_2Service {

    ResponseEntity<CategoriesProjection> addNewCategory(UUID categories1Id, Category2NamesArrayDTO category2NamesClass);

    ResponseEntity<String> deleteCategory(UUID id);



    String deleteByCategories1(UUID categories1);

    ResponseEntity<CategoriesProjection> editCategory(UUID id, Category2NamesArrayDTO category2NamesArrayDTO);

    ResponseEntity<List<Categories2Projection>> getAllCategoriesByCategories1AndLan(String lan, UUID category1Id);

    ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(UUID id);

    ResponseEntity<List<CategoriesProjection>> getStructureData(String lan);
}

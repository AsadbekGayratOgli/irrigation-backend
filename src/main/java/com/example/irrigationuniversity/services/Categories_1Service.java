package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.Category1NamesArrayDTO;
import com.example.irrigationuniversity.projection.Categories1Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface Categories_1Service {




    ResponseEntity<CategoriesProjection> addNewCategory(Category1NamesArrayDTO category1NamesClass);

    ResponseEntity<List<Categories1Projection>> getHeaderCategoriesWithChildren(String lan);

    ResponseEntity<List<Categories1Projection>> getAllCategoriesWithChildren(String lan);

    ResponseEntity<List<CategoriesProjection>> getAllCategories();

    ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(UUID id);

    CategoriesProjection getCategory(UUID id);

    ResponseEntity<String> deleteCategory(UUID id);

    ResponseEntity<CategoriesProjection> editCategory(UUID id, Category1NamesArrayDTO category1NamesArrayDTO);

}

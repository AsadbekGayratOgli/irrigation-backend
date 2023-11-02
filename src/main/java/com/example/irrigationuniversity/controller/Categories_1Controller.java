package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.payload.requestDTO.Category1NamesArrayDTO;
import com.example.irrigationuniversity.projection.Categories1Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import com.example.irrigationuniversity.services.Categories_1Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/navbar_categories")
public class Categories_1Controller {
    private final Categories_1Service categories_1Service;
    public Categories_1Controller(Categories_1Service categories_1Service) {
        this.categories_1Service = categories_1Service;
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoriesProjection> addNewCategory(@RequestBody Category1NamesArrayDTO category1NamesClass){
       return categories_1Service.addNewCategory(category1NamesClass);
    }
    @GetMapping("/header/{lan}")
    public ResponseEntity<List<Categories1Projection>> getHeaderCategoriesWithChildren(@PathVariable String lan){
        return categories_1Service.getHeaderCategoriesWithChildren(lan.toUpperCase());
    }
    @GetMapping("/all/{lan}")
    public ResponseEntity<List<Categories1Projection>> getAllCategoriesWithChildren(@PathVariable String lan){
        return categories_1Service.getAllCategoriesWithChildren(lan.toUpperCase());
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(@PathVariable UUID id){
        return categories_1Service.getCategoryWithAllNames(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CategoriesProjection>> getCategories(){
        return categories_1Service.getAllCategories();
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriesProjection> editCategory(@PathVariable UUID id, @RequestBody Category1NamesArrayDTO category1NamesArrayDTO){
        return categories_1Service.editCategory(id,category1NamesArrayDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id){
        return categories_1Service.deleteCategory(id);
    }

}


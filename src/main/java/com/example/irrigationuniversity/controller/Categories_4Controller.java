package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.payload.requestDTO.Category4NamesArrayDTO;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import com.example.irrigationuniversity.services.Categories_4Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/navbar_categories_4")
public class Categories_4Controller {
    private final Categories_4Service categories4Service;
    public Categories_4Controller(Categories_4Service categories4Service) {
        this.categories4Service = categories4Service;
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(@PathVariable UUID id) {
        return categories4Service.getCategoryWithAllNames(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("{category3Id}")
    public ResponseEntity<CategoriesProjection> addNewCategory(@PathVariable UUID category3Id, @RequestBody Category4NamesArrayDTO category4NamesDTO){
        return categories4Service.addNewCategory(category3Id,category4NamesDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id){
        return categories4Service.deleteCategory(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriesProjection> editCategory(@PathVariable UUID id, @RequestBody Category4NamesArrayDTO category4NamesArrayDTO){
        return categories4Service.editCategory(id,category4NamesArrayDTO);
    }
}

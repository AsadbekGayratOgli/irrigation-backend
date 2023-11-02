package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.payload.requestDTO.Category3NamesArrayDTO;
import com.example.irrigationuniversity.projection.*;
import com.example.irrigationuniversity.services.Categories_3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/navbar_categories_3")
public class Categories_3Controller {
    private final Categories_3Service categories_3Service;
    public Categories_3Controller(Categories_3Service categories3Service) {
        this.categories_3Service = categories3Service;
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/admin/{category2Id}")
    public ResponseEntity<List<Categories3Projection>> getCategoriesWithChildren(@PathVariable UUID category2Id) {
        return categories_3Service.getAllCategoriesByCategories2AndLan("UZ", category2Id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(@PathVariable UUID id) {
        return categories_3Service.getCategoryWithAllNames(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("/{category2Id}")
    public ResponseEntity<CategoriesProjection> addNewCategory(@PathVariable UUID category2Id, @RequestBody Category3NamesArrayDTO category3NamesDTO) {
        return categories_3Service.addNewCategory(category2Id, category3NamesDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id) {
        return categories_3Service.deleteCategory(id);
    }
    @GetMapping("/foreign_partners")
    public ResponseEntity<UUID> getIdOfForeignPartners(){
        return categories_3Service.getIdOfForeignPartners();
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriesProjection> editCategory(@PathVariable UUID id, @RequestBody Category3NamesArrayDTO category3NamesArrayDTO) {
        return categories_3Service.editCategory(id, category3NamesArrayDTO);
    }
}



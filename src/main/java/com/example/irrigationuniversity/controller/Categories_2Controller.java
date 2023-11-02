package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.payload.requestDTO.Category2NamesArrayDTO;
import com.example.irrigationuniversity.projection.Categories2Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import com.example.irrigationuniversity.services.Categories_2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/navbar_categories_2")
public class Categories_2Controller {
    private final Categories_2Service categories_2Service;
    public Categories_2Controller(Categories_2Service categories2Service) {
        this.categories_2Service = categories2Service;
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/admin/{category1Id}")
    public ResponseEntity<List<Categories2Projection>> getCategoriesWithChildren(@PathVariable UUID category1Id){
        return categories_2Service.getAllCategoriesByCategories1AndLan("UZ",category1Id);
    }
    @GetMapping("/structure/{lan}")
    public ResponseEntity<List<CategoriesProjection>> getStructureData(@PathVariable String lan){
        return categories_2Service.getStructureData(lan.toUpperCase());
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(@PathVariable UUID id){
        return categories_2Service.getCategoryWithAllNames(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping("/{category1Id}")
    public ResponseEntity<CategoriesProjection> addNewCategory(@PathVariable UUID category1Id, @RequestBody Category2NamesArrayDTO category2NamesDTO){
        return categories_2Service.addNewCategory(category1Id,category2NamesDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id){
        return categories_2Service.deleteCategory(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriesProjection> editCategory(@PathVariable UUID id, @RequestBody Category2NamesArrayDTO category2NamesArrayDTO){
        return categories_2Service.editCategory(id,category2NamesArrayDTO);
    }
}

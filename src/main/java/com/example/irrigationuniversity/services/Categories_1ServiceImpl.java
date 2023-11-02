package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.Category1NamesDTO;
import com.example.irrigationuniversity.entity.Categories1;
import com.example.irrigationuniversity.projection.Categories1Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import com.example.irrigationuniversity.repository.Categories1NameRepository;
import com.example.irrigationuniversity.repository.Categories_1Repository;
import com.example.irrigationuniversity.payload.requestDTO.Category1NamesArrayDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Categories_1ServiceImpl implements Categories_1Service {
    private final Categories_2Service categories2Service;
    private final Categories_1Repository categories_1Repository;
    private final Categories1NameRepository categories1NameRepository;
    private final ContentService contentService;


    @Override
    public ResponseEntity<CategoriesProjection> addNewCategory(Category1NamesArrayDTO category1NamesClass){
        Categories1 categories1 = new Categories1();
        categories1.setCreatedAt(LocalDateTime.now());
        Categories1 save = categories_1Repository.save(categories1);
        for (Category1NamesDTO categories1NameDTO : category1NamesClass.getCategory1NamesDTO()) {
            categories1NameDTO.getCategories1Names().setCategories1(save);
            categories1NameRepository.save(categories1NameDTO.getCategories1Names());
        }
        return ResponseEntity.ok().body(categories_1Repository.findByIdAndLan(save.getId()));
    }
    @Override
    public ResponseEntity<List<Categories1Projection>> getHeaderCategoriesWithChildren(String lan){
        return ResponseEntity.ok().body(categories_1Repository.findAllByOrdAndLan(lan));
    }
    @Override
    public ResponseEntity<List<Categories1Projection>> getAllCategoriesWithChildren(String lan) {
        return ResponseEntity.ok().body(categories_1Repository.findAllByLan(lan));
    }
    @Override
    public ResponseEntity<List<CategoriesProjection>> getAllCategories(){
        return ResponseEntity.ok().body(categories_1Repository.findAllCategories1ByLan("UZ"));
    }

    @Override
    public ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(UUID id){
        return ResponseEntity.ok().body(categories_1Repository.findByIdWithAllNames(id));
    }
    @Override
    public CategoriesProjection getCategory(UUID id){
        return categories_1Repository.findByIdAndLan(id);
    }
    @Override
    public ResponseEntity<String> deleteCategory(UUID id){
        contentService.deleteAllContents(id);
        categories2Service.deleteByCategories1(id);
        categories1NameRepository.deleteByCategory1Id(id);
        categories_1Repository.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public ResponseEntity<CategoriesProjection> editCategory(UUID id, Category1NamesArrayDTO category1NamesArrayDTO){
        System.out.println(id);
        Optional<Categories1> categories1 = categories_1Repository.findById(id);
        System.out.println(category1NamesArrayDTO);
        for (Category1NamesDTO category1NameDTO : category1NamesArrayDTO.getCategory1NamesDTO()) {
            category1NameDTO.getCategories1Names().setId(category1NameDTO.getId());
            category1NameDTO.getCategories1Names().setCategories1(categories1.get());
            categories1NameRepository.save(category1NameDTO.getCategories1Names());
        }
        return ResponseEntity.ok().body(categories_1Repository.findByIdAndLan(id));
    }


}

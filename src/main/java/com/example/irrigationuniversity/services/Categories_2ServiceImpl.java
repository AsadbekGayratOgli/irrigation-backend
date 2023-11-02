package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.payload.requestDTO.Category2NamesArrayDTO;
import com.example.irrigationuniversity.payload.requestDTO.Category2NamesDTO;
import com.example.irrigationuniversity.entity.Categories2;
import com.example.irrigationuniversity.projection.Categories2Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import com.example.irrigationuniversity.repository.Categories2NameRepository;
import com.example.irrigationuniversity.repository.Categories_1Repository;
import com.example.irrigationuniversity.repository.Categories_2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Categories_2ServiceImpl implements Categories_2Service {
    private final Categories_1Repository categories_1Repository;
    private final Categories_2Repository categories_2Repository;
    private final Categories2NameRepository categories2NameRepository;
    private final Categories_3Service categories3Service;
    private final ContentService contentService;
    @Override
    public ResponseEntity<CategoriesProjection> addNewCategory(UUID categories1Id, Category2NamesArrayDTO category2NamesClass){
        Categories2 categories2 = new Categories2();
        categories2.setCreatedAt(LocalDateTime.now());
        categories2.setCategories1(categories_1Repository.findById(categories1Id).get());
        Categories2 save = categories_2Repository.save(categories2);
        for (Category2NamesDTO categories2NameDTO : category2NamesClass.getCategory2NamesDTO()) {
            categories2NameDTO.getCategories2Names().setCategories2(save);
            categories2NameRepository.save(categories2NameDTO.getCategories2Names());
        }
        return ResponseEntity.ok().body(categories_2Repository.findByIdAndLan(save.getId()));
    }
    @Override
    public ResponseEntity<String> deleteCategory(UUID id){
        contentService.deleteAllContents(id);
        categories3Service.deleteByCategories2(id);
        categories2NameRepository.deleteByCategory2Id(id);
        categories_2Repository.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public String deleteByCategories1(UUID category1Id){
        for (UUID uuid : categories_2Repository.findAllByCategories1(category1Id)) {
            contentService.deleteAllContents(uuid);
            categories3Service.deleteByCategories2(uuid);
            categories2NameRepository.deleteByCategory2Id(uuid);
        }
        categories_2Repository.deleteByCategories1(category1Id);
        return "Successfully deleted!";
    }
    @Override
    public ResponseEntity<CategoriesProjection> editCategory(UUID id, Category2NamesArrayDTO category2NamesArrayDTO){
        System.out.println(category2NamesArrayDTO);
        Optional<Categories2> categories2 = categories_2Repository.findById(id);
        for (Category2NamesDTO category2NameDTO : category2NamesArrayDTO.getCategory2NamesDTO()) {
            category2NameDTO.getCategories2Names().setId(category2NameDTO.getId());
            category2NameDTO.getCategories2Names().setCategories2(categories2.get());
            categories2NameRepository.save(category2NameDTO.getCategories2Names());
        }
        return ResponseEntity.ok().body(categories_2Repository.findByIdAndLan(id));
    }
    @Override
    public ResponseEntity<List<Categories2Projection>> getAllCategoriesByCategories1AndLan(String lan, UUID category1Id){
     return ResponseEntity.ok().body(categories_2Repository.findAllByCategories1AndLan(lan,category1Id));

    }
    @Override
    public ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(UUID id){
        return ResponseEntity.ok().body(categories_2Repository.findByIdWithAllNames(id));
    }
    @Override
    public ResponseEntity<List<CategoriesProjection>> getStructureData(String lan) {
        List<UUID> categoriesIds = new ArrayList<>(List.of(UUID.fromString("3c2b8e7f-9b9a-4de7-88ab-4bf1f7bf035a")
                , UUID.fromString("c24f3149-736d-4ff7-a5b9-a2ef831934bb")
                , UUID.fromString("7c9e5f5e-5838-4126-9d2e-657d07265b96")
                , UUID.fromString("aeba2772-59b0-4056-b700-2aeaaff272cb")
                , UUID.fromString("9aae133b-ee93-4746-bb32-736ec7606a22")
                , UUID.fromString("af8ba1c6-abfb-4bb7-b179-e76bd091b8d1")

        ));
        System.out.println(categoriesIds.toString());
        List<CategoriesProjection> categoriesNames = new ArrayList<>();
        for (UUID categoryId : categoriesIds) {
            categoriesNames.add(categories_2Repository.findStructureData(categoryId,lan));
        }
        return ResponseEntity.ok().body(categoriesNames);
    }
}

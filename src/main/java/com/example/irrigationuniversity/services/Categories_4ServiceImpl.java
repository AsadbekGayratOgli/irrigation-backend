package com.example.irrigationuniversity.services;
import com.example.irrigationuniversity.payload.requestDTO.Category4NamesArrayDTO;
import com.example.irrigationuniversity.payload.requestDTO.Category4NamesDTO;
import com.example.irrigationuniversity.entity.Categories4;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import com.example.irrigationuniversity.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class Categories_4ServiceImpl implements Categories_4Service{
    private final Categories_3Repository categories_3Repository;
    private final Categories_4Repository categories_4Repository;
    private final Categories4NameRepository categories4NameRepository;
    private final ContentService contentService;
    @Override
    public ResponseEntity<CategoriesProjection> addNewCategory(UUID categories3Id, Category4NamesArrayDTO category4NamesClass){
        Categories4 categories4 = new Categories4();
        categories4.setCreatedAt(LocalDateTime.now());
        categories4.setCategories3(categories_3Repository.findById(categories3Id).get());
        categories_4Repository.save(categories4);
        for (Category4NamesDTO categories4NameDTO: category4NamesClass.getCategory4NamesDTO()) {
            categories4NameDTO.getCategories4Names().setCategories4(categories4);
            categories4NameRepository.save(categories4NameDTO.getCategories4Names());
        }
        return ResponseEntity.ok().body(categories_4Repository.findByIdAndLan(categories4.getId()));
    }
    @Override
    public ResponseEntity<List<CategoriesLanProjection>> getCategoryWithAllNames(UUID id){
        return ResponseEntity.ok().body(categories_4Repository.findByIdWithAllNames(id));
    }
    @Override
    public ResponseEntity<String> deleteCategory(UUID id){
        contentService.deleteAllContents(id);
        categories4NameRepository.deleteByCategory4Id(id);
        categories_4Repository.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted!");
    }
    @Override
    public String deleteByCategories3(UUID category3Id){
        for (UUID uuid : categories_4Repository.findAllByCategories3(category3Id)) {
            contentService.deleteAllContents(uuid);
            categories4NameRepository.deleteByCategory4Id(uuid);
        }
        categories_4Repository.deleteByCategories3Id(category3Id);
        return "Successfully deleted!";
    }

    @Override
    public ResponseEntity<CategoriesProjection> editCategory(UUID id, Category4NamesArrayDTO category4NamesArrayDTO){
        Optional<Categories4> categories4 = categories_4Repository.findById(id);
        for (Category4NamesDTO category4NameDTO : category4NamesArrayDTO.getCategory4NamesDTO()) {
            category4NameDTO.getCategories4Names().setId(category4NameDTO.getId());
            category4NameDTO.getCategories4Names().setCategories4(categories4.get());
            categories4NameRepository.save(category4NameDTO.getCategories4Names());
        }
        return ResponseEntity.ok().body(categories_4Repository.findByIdAndLan(id));

    }
}


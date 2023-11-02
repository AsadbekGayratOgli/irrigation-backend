package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Categories2;
import com.example.irrigationuniversity.projection.Categories1Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.Categories2Projection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface Categories_2Repository extends JpaRepository<Categories2, UUID> {

    @Query(value = "select  c2.id as id,c2n.name as name, c2n.lan as lan from categories2 c2 join categories2names c2n on c2.id = c2n.categories2_id and c2n.lan = ?1 and c2.categories1_id = ?2 order by c2.created_at",nativeQuery = true)
    List<Categories2Projection> findAllByCategories1AndLan(String lan, UUID category1Id);
    @Query(value = "select  c2n.id as id,c2n.name as name,c2n.lan as lan from categories2 c2 join categories2names c2n on c2.id = c2n.categories2_id and c2.id = ?1 order by c2.created_at",nativeQuery = true)
    List<CategoriesLanProjection> findByIdWithAllNames(UUID id);
    @Modifying
    @Transactional
    @Query(value = "delete from categories2 where categories1_id = ?1 ",nativeQuery = true)
    void deleteByCategories1(UUID category1id);
    @Query(value = "select c2.id as id,c2n.name as name, c2n.lan as lan from categories2 c2 join categories2names c2n on c2.id = c2n.categories2_id and c2n.lan = 'UZ' and c2.id = ?1",nativeQuery = true)
    CategoriesProjection findByIdAndLan(UUID id);
    @Query(value = "select id from categories2  where categories1_id = ?1",nativeQuery = true)
    List<UUID> findAllByCategories1(UUID category1Id);
    @Query(value = """
                select c2.id as id, c2n.name as name, c2n.lan as lan from categories2names c2n join categories2 c2 on c2.id = c2n.categories2_id where c2.id = ?1 and c2n.lan = ?2
               """,nativeQuery = true)
    CategoriesProjection findStructureData(UUID id, String lan);
}

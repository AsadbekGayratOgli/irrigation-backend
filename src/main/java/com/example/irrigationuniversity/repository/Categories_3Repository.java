package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Categories3;
import com.example.irrigationuniversity.projection.Categories3Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

public interface Categories_3Repository extends JpaRepository<Categories3, UUID> {
    @Query(value = "select  c3.id as id,c3n.name as name, c3n.lan as lan from categories3 c3 join categories3names c3n on c3.id = c3n.categories3_id and c3n.lan = ?1 and c3.categories2_id = ?2 order by c3.created_at",nativeQuery = true)
    List<Categories3Projection> findAllByCategories2AndLan(String lan, UUID category2Id);
    @Query(value = "select  c3n.id as id,c3n.name as name,c3n.lan as lan from categories3 c3 join categories3names c3n on c3.id = c3n.categories3_id and c3.id = ?1 order by c3.created_at",nativeQuery = true)
    List<CategoriesLanProjection> findByIdWithAllNames(UUID id);
    @Modifying
    @Transactional
    @Query(value = "delete from categories3 where categories2_id = ?1 ",nativeQuery = true)
    void deleteByCategories2(UUID category2id);
    @Query(value = "select c3.id as id,c3n.name as name, c3n.lan as lan from categories3 c3 join categories3names c3n on c3.id = c3n.categories3_id and c3n.lan = 'UZ' and c3.id = ?1",nativeQuery = true)
    CategoriesProjection findByIdAndLan(UUID id);
    @Query(value = "select id from categories3  where categories2_id = ?1",nativeQuery = true)
    List<UUID> findAllByCategories2(UUID category2Id);
}
//    @Modifying
//    @Transactional
//    @Query(value = "delete from categories3 where categories2_id = ?1",nativeQuery = true)
//    void deleteByCategories2(UUID category2Id);
//    @Modifying
//    @Transactional
//    @Query(value = "delete from categories3 c3 using categories2 c2 where c2.categories1_id = ?1",nativeQuery = true)
//    void deleteByCategories1(UUID category1Id);
//    @Query(value = "select  c.id as id,c3n.name as name, c3n.lan as lan from categories3 c join categories3names c3n on c.id = c3n.categories3_id and c3n.lan = 'UZ' and c.id = ?1",nativeQuery = true)
//    CategoriesProjection findByIdAndLan(UUID id);
//    @Query(value = "select  c3n.id,c3n.name, c3n.lan as lan  from categories3 c join categories3names c3n on c.id = c3n.categories3_id and c.id = ?1",nativeQuery = true)
//    List<CategoriesLanProjection> findByIdWithAllNames(UUID id);
//    @Query(value = "select  c3.id as id,c3n.name as name, c3n.lan as lan  from categories3 c3 join categories3names c3n on c3.id = c3n.categories3_id and c3n.lan = ?1 and c3.categories2_id = ?2",nativeQuery = true)
//    List<CategoriesProjection> findByCategories2AndLan(String lan, UUID categories2Id);

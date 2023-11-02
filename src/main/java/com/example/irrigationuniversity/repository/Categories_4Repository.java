package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Categories4;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface Categories_4Repository extends JpaRepository<Categories4, UUID> {
    @Modifying
    @Transactional
    @Query(value = "delete from categories4 where categories3_id = ?1",nativeQuery = true)
    void deleteByCategories3Id(UUID category3Id);
    @Query(value = "select  c.id as id,c4n.name as name, c4n.lan as lan from categories4 c join categories4names c4n on c.id = c4n.categories4_id and c4n.lan = 'UZ' and c.id = ?1",nativeQuery = true)
    CategoriesProjection findByIdAndLan(UUID id);
    @Query(value = "select  c4n.id,c4n.name, c4n.lan as lan  from categories4 c4 join categories4names c4n on c4.id = c4n.categories4_id and c4.id = ?1 order by c4.created_at",nativeQuery = true)
    List<CategoriesLanProjection> findByIdWithAllNames(UUID id);
    @Query(value = "select  c4.id as id,c4n.name as name, c4n.lan as lan  from categories4 c4 join categories4names c4n on c4.id = c4n.categories4_id and c4n.lan = ?1 and c4.categories3_id = ?2 order by c4.created_at",nativeQuery = true)
    List<CategoriesProjection> findAllByCategories3AndLan(String lan, UUID categories3Id);
    @Query(value = "select id from categories4  where categories3_id = ?1",nativeQuery = true)
    List<UUID> findAllByCategories3(UUID category3Id);
}

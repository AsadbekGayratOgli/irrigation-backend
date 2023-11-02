package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Categories1;
import com.example.irrigationuniversity.projection.Categories1Projection;
import com.example.irrigationuniversity.projection.CategoriesLanProjection;
import com.example.irrigationuniversity.projection.CategoriesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface Categories_1Repository extends JpaRepository<Categories1, UUID> {
    @Query(value = "select c1n.id as id, c1n.name as name, c1n.lan as lan  from categories1 c1 join categories1names c1n on c1.id = c1n.categories1_id and c1.id = ?1 order by c1.created_at",nativeQuery = true)
    List<CategoriesLanProjection> findByIdWithAllNames(UUID id);
    @Query(value = "select c1.id as id,c1n.name as name, c1n.lan as lan from categories1 c1 join categories1names c1n on c1.id = c1n.categories1_id and c1n.lan = 'UZ' and c1.id = ?1",nativeQuery = true)
    CategoriesProjection findByIdAndLan(UUID id);
    @Query(value = "select c1.id as id, c1n.name as name, c1n.lan as lan from categories1 c1 join categories1names c1n on c1.id = c1n.categories1_id and c1n.lan = ?1 order by c1.created_at",nativeQuery = true)
    List<Categories1Projection> findAllByLan(String lan);
    @Query(value = "select c1.id as id, c1n.name as name, c1n.lan as lan from categories1 c1 join categories1names c1n on c1.id = c1n.categories1_id and c1n.lan = ?1 order by c1.created_at",nativeQuery = true)
    List<CategoriesProjection> findAllCategories1ByLan(String lan);
    @Query(value = "select c1.id as id, c1n.name as name, c1n.lan as lan from categories1 c1 join categories1names c1n on c1.id = c1n.categories1_id and c1n.lan = ?1 and c1.is_in_header = true order by c1.created_at",nativeQuery = true)
    List<Categories1Projection> findAllByOrdAndLan(String lan);

}

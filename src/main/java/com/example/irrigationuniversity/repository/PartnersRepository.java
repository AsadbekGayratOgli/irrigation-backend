package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Partners;
import com.example.irrigationuniversity.projection.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PartnersRepository extends JpaRepository<Partners, UUID> {

    @Query(value = "insert into partners(id,content_id) values(?1,?2) RETURNING id",nativeQuery = true)
    UUID save(UUID id, UUID content_id);

    @Query(value = "select p.id, cd.title, cd.lan from partners p join content c on p.content_id = c.id join content_data cd on c.id = cd.content_id and cd.lan = ?1 ",nativeQuery = true)
    List<PartnersWithLessDataProjection> findAll(String lan);
    @Query(value = "select p.id, c.created_at, c.author, c.views, cd.title, cd.lan, cd.body from partners p join content c on p.content_id = c.id and p.id = ?1 join content_data cd on c.id = cd.content_id  and cd.lan = ?2",nativeQuery = true)
    PartnersWithDataWithoutLogoProjection findByIdWithContentDataWithoutLogo(UUID partnerId, String lan);
    @Query(value = "select p.id as id, c.created_at as created_at, c.author as author, c.views as views from partners p join content c on c.id = p.content_id  where p.id = ?1 ",nativeQuery = true)
    PartnersWithAllDataProjection findAllByIdWithAllData(UUID id);

}

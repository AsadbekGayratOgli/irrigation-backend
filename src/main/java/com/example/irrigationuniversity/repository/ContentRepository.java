package com.example.irrigationuniversity.repository;
import com.example.irrigationuniversity.entity.Content;
import com.example.irrigationuniversity.projection.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    @Query(value = "select c.id as id, c.created_at as created_at, c.author as author, c.views as views, cd.title as title, cd.lan as lan  from content c join categories1_content cc on c.id = cc.content_id and cc.categories1_id = ?1  join content_data cd on c.id = cd.content_id and cd.lan = ?3 order by c.created_at desc offset (?2 - 1)*6 limit ?4",nativeQuery = true)
    List<ContentProjection> findGroupOfContentsOfCategories1ByLan(UUID categoryId, Integer page,String lan,int limit);
    @Query(value = "select c.id as id, c.created_at as created_at, c.author as author, c.views as views, cd.title as title, cd.lan as lan  from content c join categories2_content cc on c.id = cc.content_id and cc.categories2_id = ?1 join content_data cd on c.id = cd.content_id and cd.lan = ?3 order by c.created_at desc offset (?2 - 1)*6 limit ?4",nativeQuery = true)
    List<ContentProjection> findGroupOfContentsOfCategories2ByLan(UUID categoryId, Integer page,String lan,int limit);
    @Query(value = "select c.id as id, c.created_at as created_at, c.author as author, c.views as views, cd.title as title, cd.lan as lan  from content c join categories3_content cc on c.id = cc.content_id and cc.categories3_id = ?1 join content_data cd on c.id = cd.content_id and cd.lan = ?3 order by c.created_at desc offset (?2 - 1)*6 limit ?4",nativeQuery = true)
    List<ContentProjection> findGroupOfContentsOfCategories3ByLan(UUID categoryId, Integer page,String lan,int limit);
    @Query(value = "select c.id as id, c.created_at as created_at, c.author as author, c.views as views, cd.title as title, cd.lan as lan  from content c join categories3_content cc on c.id = cc.content_id and cc.categories3_id = ?1 join content_data cd on c.id = cd.content_id and cd.lan = ?2 order by c.created_at desc ",nativeQuery = true)
    List<ContentWithLessDataProjection> findAllContentsByCategoryId(UUID categoryId,String lan);
    @Query(value = "select * from content c join categories1_content cc on c.id = cc.content_id where cc.categories1_id = ?1",nativeQuery = true)
    List<Content> findAllByCategoryId(UUID categoryId);
    @Query(value = "select c.id as id, c.created_at as created_at, c.author as author, c.views as views, cd.title as title, cd.lan as lan, cd.body as body  from content c join content_data cd on c.id = cd.content_id and c.id = ?1 and cd.lan = ?2",nativeQuery = true)
    ContentWithDataProjection findByIdWithContentData(UUID contentId, String lan);
    @Query(value = "select case when count(*) > 0 then (count(*)/6)+1 else 0 end from content c join categories1_content cc on c.id = cc.content_id where cc.categories1_id = ?1 group by cc.categories1_id;",nativeQuery = true)
    Integer countByCategories1Id(UUID categoryId);
    @Query(value = "select case when count(*) > 0 then (count(*)/6)+1 else 0 end  from content c join categories2_content cc on c.id = cc.content_id where cc.categories2_id = ?1 group by cc.categories2_id;",nativeQuery = true)
    Integer countByCategories2Id(UUID categoryId);
    @Query(value = "select case when count(*) > 0 then (count(*)/6)+1 else 0 end  from content c join categories3_content cc on c.id = cc.content_id where cc.categories3_id = ?1 group by cc.categories3_id;",nativeQuery = true)
    Integer countByCategories3Id(UUID categoryId);
    @Query(value = "select c.id as id, c.created_at as created_at, c.author as author, c.views as views from content c where c.id = ?1 ",nativeQuery = true)
    ContentWithAllDataProjection findAllByIdWithAllData(UUID id);
    @Modifying
    @Transactional
    @Query(value = "update content set views = views+1 where  id = ?1" ,nativeQuery = true)
    void updateViews( UUID id);
    @Query(value = "select c.id as id, cd.title as title from content c join content_data cd  on c.id = cd.content_id where lower(cd.title) like '%'|| ?1 ||'%' and cd.lan = ?2" ,nativeQuery = true)
    List<ContentSearchProjection> searchContentByTitle(String contentTitlePart, String lan);
    @Modifying
    @Transactional
    @Query(value = "delete from  categories1_content cc  where cc.content_id = ?1",nativeQuery = true)
    void deleteFromCategories1_content(UUID contentId);
    @Modifying
    @Transactional
    @Query(value = "delete from  categories2_content cc where cc.content_id = ?1",nativeQuery = true)
    void deleteFromCategories2_content(UUID contentId);
    @Modifying
    @Transactional
    @Query(value = "delete from  categories3_content cc where cc.content_id = ?1",nativeQuery = true)
    void deleteFromCategories3_content(UUID contentId);
    @Query(value = "select c.id as id, c.created_at as created_at, c.author as author, c.views as views, cd.title as title, cd.lan as lan  from content c join categories2_content cc on c.id = cc.content_id and cc.categories2_id = ?1 and cc.content_id <> ?2 join content_data cd on c.id = cd.content_id and cd.lan = ?3 order by c.created_at desc limit 5", nativeQuery = true)
    List<ContentProjection> findGroupOfNewsContents(UUID newsId, UUID id, String lan);

}

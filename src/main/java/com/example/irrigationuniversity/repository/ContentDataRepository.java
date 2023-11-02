package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.ContentData;
import com.example.irrigationuniversity.projection.ContentDataProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ContentDataRepository extends JpaRepository<ContentData,UUID> {
    @Query(value = "select * from content_data where content_id =?1 and lan = ?2",nativeQuery = true)
    ContentData findByContentIdAndLan(UUID contentID, String lan);
    @Modifying
    @Transactional
    @Query(value = "delete from content_data where content_id = ?1",nativeQuery = true)
    void deleteByContentId(UUID contentId);
    @Query(value = "select id from content_data where content_id =?1",nativeQuery = true)
    List<ContentDataProjection> findByContentId(UUID contentId);
    @Query(value = "select * from content_data where id =?1",nativeQuery = true)
    ContentData getContentDataByID(UUID id);

    @Query(value = "select cd.id from content_data cd join partners p on cd.content_id = p.content_id where p.id =?1",nativeQuery = true)
    List<ContentDataProjection> findByPartnerId(UUID partnerId);
}

package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query(value = "select * from message order by created_at desc", nativeQuery = true)
    List<Message> findAllByOrder();
}

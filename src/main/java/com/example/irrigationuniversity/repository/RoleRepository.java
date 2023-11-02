package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Roles, UUID> {
    @Query(value = "select * from roles where id = ?1",nativeQuery = true)
    List<Roles> findRolesListById(UUID id);
}

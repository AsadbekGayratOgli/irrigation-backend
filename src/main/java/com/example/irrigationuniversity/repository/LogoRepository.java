package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.Logo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface LogoRepository extends JpaRepository<Logo, UUID> {
    @Query(value = " select '/logo/'|| url from logo" ,nativeQuery = true)
    String findUrlById();
}

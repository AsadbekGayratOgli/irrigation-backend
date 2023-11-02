package com.example.irrigationuniversity.repository;

import com.example.irrigationuniversity.entity.AdmissionYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdmissionYearRepository extends JpaRepository<AdmissionYear, UUID> {
}

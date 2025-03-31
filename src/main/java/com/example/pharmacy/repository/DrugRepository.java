package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.MedicationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<MedicationsEntity, Long> {
}

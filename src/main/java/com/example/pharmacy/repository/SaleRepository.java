package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SalesEntity, Long> {
}

package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SalesEntity, Long> {
    List<SalesEntity> findByCustomerId(int customerId);
}

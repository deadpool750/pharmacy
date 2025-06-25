package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.SuppliersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing supplier data.
 * Provides standard CRUD operations via JpaRepository.
 */
@Repository
public interface SupplierRepository extends JpaRepository<SuppliersEntity, Long> {
}

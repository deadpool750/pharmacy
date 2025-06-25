package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing employee data.
 * Provides standard CRUD operations via JpaRepository.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeesEntity, Long> {
}

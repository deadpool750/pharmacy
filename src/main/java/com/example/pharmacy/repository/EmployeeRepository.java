package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeesEntity, Long> {
}

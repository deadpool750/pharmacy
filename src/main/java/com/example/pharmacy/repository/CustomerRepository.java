package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomersEntity, Long> {
}

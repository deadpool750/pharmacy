package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomersEntity, Integer> {
    Optional<CustomersEntity> findByEmail(String email);
}

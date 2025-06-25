package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing customer data.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomersEntity, Integer> {

    /**
     * Finds a customer by their email address.
     *
     * @param email the email to search for
     * @return an Optional containing the matching customer, if found
     */
    Optional<CustomersEntity> findByEmail(String email);
}

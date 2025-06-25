package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing sales data.
 * Provides methods for retrieving sales by customer ID.
 */
@Repository
public interface SaleRepository extends JpaRepository<SalesEntity, Long> {

    /**
     * Finds all sales associated with a given customer ID.
     *
     * @param customerId the ID of the customer
     * @return a list of sales made by the specified customer
     */
    List<SalesEntity> findByCustomerId(int customerId);
}

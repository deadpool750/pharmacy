package com.example.pharmacy.controller;

import com.example.pharmacy.controller.dto.suppliers.CreateSupplierDto;
import com.example.pharmacy.controller.dto.suppliers.CreateSupplierResponseDto;
import com.example.pharmacy.controller.dto.suppliers.GetSupplierDto;
import com.example.pharmacy.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing supplier-related operations.
 * Accessible only by users with ADMIN role.
 */
@RestController
@RequestMapping("/api/suppliers")
@PreAuthorize("hasRole('ADMIN')")
public class SupplierController {

    /** Service for handling supplier-related logic. */
    private final SupplierService supplierService;

    /**
     * Constructs the SupplierController with the provided SupplierService.
     *
     * @param supplierService the supplier service
     */
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /**
     * Retrieves all suppliers.
     *
     * @return a list of all suppliers
     */
    @GetMapping
    public List<GetSupplierDto> getAllSuppliers() {
        return supplierService.getAll();
    }

    /**
     * Retrieves a specific supplier by ID.
     *
     * @param id the supplier ID
     * @return the supplier data
     */
    @GetMapping("/{id}")
    public GetSupplierDto getSupplier(@PathVariable long id) {
        return supplierService.getOne(id);
    }

    /**
     * Creates a new supplier.
     *
     * @param dto the supplier data to create
     * @return the created supplier DTO with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<CreateSupplierResponseDto> createSupplier(@RequestBody CreateSupplierDto dto) {
        var supplier = supplierService.create(dto);
        return new ResponseEntity<>(supplier, HttpStatus.CREATED);
    }

    /**
     * Updates an existing supplier by ID.
     *
     * @param id the supplier ID
     * @param dto the updated supplier data
     * @return the updated supplier DTO with HTTP 200 status
     */
    @PutMapping("/{id}")
    public ResponseEntity<GetSupplierDto> updateSupplier(@PathVariable long id, @RequestBody CreateSupplierDto dto) {
        var updatedSupplier = supplierService.update(id, dto);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }

    /**
     * Deletes a supplier by ID.
     *
     * @param id the supplier ID to delete
     * @return a 204 No Content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

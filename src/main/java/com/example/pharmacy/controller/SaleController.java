package com.example.pharmacy.controller;

import com.example.pharmacy.controller.dto.sale.CreateSaleDto;
import com.example.pharmacy.controller.dto.sale.CreateSaleResponseDto;
import com.example.pharmacy.controller.dto.sale.GetSaleDto;
import com.example.pharmacy.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing sales records.
 * Provides endpoints for both admins and customers.
 */
@RestController
@RequestMapping("/api/sales")
public class SaleController {

    /** Service for handling sale-related operations. */
    private final SaleService saleService;

    /**
     * Constructs the SaleController with the given SaleService.
     *
     * @param saleService the sale service
     */
    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    /**
     * Retrieves all sales. Accessible only to admins.
     *
     * @return a list of all sales
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<GetSaleDto> getAllSales() {
        return saleService.getAll();
    }

    /**
     * Retrieves a specific sale by ID. Accessible only to admins.
     *
     * @param id the ID of the sale
     * @return the sale details
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GetSaleDto getSale(@PathVariable long id) {
        return saleService.getOne(id);
    }

    /**
     * Deletes a sale by ID. Accessible only to admins.
     *
     * @param id the ID of the sale to delete
     * @return a 204 No Content response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSale(@PathVariable long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all sales for the currently authenticated customer.
     *
     * @param authHeader the Authorization header containing the Bearer token
     * @return a list of the customer's sales
     */
    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<GetSaleDto> getOwnSales(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Strip "Bearer "
        return saleService.getSalesForCurrentCustomer(token);
    }
}

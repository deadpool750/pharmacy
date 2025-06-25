package com.example.pharmacy.controller;

import com.example.pharmacy.controller.dto.customer.CreateCustomerDto;
import com.example.pharmacy.controller.dto.customer.CreateCustomerResponseDto;
import com.example.pharmacy.controller.dto.customer.GetCustomerDto;
import com.example.pharmacy.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing customer-related operations.
 * Accessible only by users with ADMIN role.
 */
@RestController
@RequestMapping("/api/customers")
@PreAuthorize("hasRole('ADMIN')")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    /**
     * Constructs the CustomerController with the provided CustomerService.
     *
     * @param customerService the service for handling customer logic
     */
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves all customers.
     *
     * @return a list of GetCustomerDto objects
     */
    @GetMapping
    public List<GetCustomerDto> getAll() {
        logger.info("Received request to fetch all customers");
        return customerService.getAll();
    }

    /**
     * Retrieves a specific customer by ID.
     *
     * @param id the ID of the customer
     * @return the customer data
     */
    @GetMapping("/{id}")
    public GetCustomerDto getOne(@PathVariable int id) {
        logger.info("Received request to fetch customer with ID: {}", id);
        return customerService.getOne(id);
    }

    /**
     * Creates a new customer.
     *
     * @param customerDto the customer data to create
     * @return the created customer response
     */
    @PostMapping
    public ResponseEntity<CreateCustomerResponseDto> create(@RequestBody CreateCustomerDto customerDto) {
        logger.info("Received request to create customer: {}", customerDto);
        var createdCustomer = customerService.create(customerDto);
        logger.info("Customer created successfully with ID: {}", createdCustomer.getId());
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    /**
     * Deletes a customer by ID.
     *
     * @param id the ID of the customer to delete
     * @return a 204 No Content response on success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        logger.info("Received request to delete customer with ID: {}", id);
        customerService.delete(id);
        logger.info("Customer deleted successfully");
        return ResponseEntity.noContent().build();
    }
}

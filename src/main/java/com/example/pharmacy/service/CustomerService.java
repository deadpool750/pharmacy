package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.customer.CreateCustomerDto;
import com.example.pharmacy.controller.dto.customer.CreateCustomerResponseDto;
import com.example.pharmacy.controller.dto.customer.GetCustomerDto;
import com.example.pharmacy.infrastructure.entity.CustomersEntity;
import com.example.pharmacy.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<GetCustomerDto> getAll() {
        logger.info("Fetching all customers from database");
        var customers = customerRepository.findAll();
        return customers.stream()
                .map(c -> new GetCustomerDto(c.getId(), c.getName(), c.getPhone(), c.getEmail()))
                .collect(Collectors.toList());
    }

    public GetCustomerDto getOne(int id) {
        logger.info("Fetching customer with ID: {}", id);
        var customer = customerRepository.findById((long) id)
                .orElseThrow(() -> {
                    logger.warn("Customer not found with ID: {}", id);
                    return new RuntimeException("Customer not found");
                });

        return new GetCustomerDto(customer.getId(), customer.getName(), customer.getPhone(), customer.getEmail());
    }

    public CreateCustomerResponseDto create(CreateCustomerDto dto) {
        logger.info("Creating new customer: {}", dto);
        var entity = new CustomersEntity();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());

        var saved = customerRepository.save(entity);
        logger.info("Customer saved to database with ID: {}", saved.getId());

        return new CreateCustomerResponseDto(saved.getId(), saved.getName(), saved.getPhone(), saved.getEmail());
    }

    public void delete(int id) {
        logger.info("Attempting to delete customer with ID: {}", id);
        if (!customerRepository.existsById((long) id)) {
            logger.warn("Customer not found for deletion with ID: {}", id);
            throw new RuntimeException("Customer not found");
        }

        customerRepository.deleteById((long) id);
        logger.info("Customer with ID {} deleted successfully", id);
    }
}

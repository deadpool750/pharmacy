package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.sale.CreateSaleDto;
import com.example.pharmacy.controller.dto.sale.CreateSaleResponseDto;
import com.example.pharmacy.controller.dto.sale.GetSaleDto;
import com.example.pharmacy.infrastructure.entity.SalesEntity;
import com.example.pharmacy.infrastructure.entity.UserEntity;
import com.example.pharmacy.repository.CustomerRepository;
import com.example.pharmacy.repository.DrugRepository;
import com.example.pharmacy.repository.SaleRepository;
import com.example.pharmacy.repository.IUserRepository;
import com.example.pharmacy.service.inputs.SaleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing medication sales and retrieving related data.
 */
@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final JwtService jwtService;
    private final IUserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final DrugRepository drugRepository;

    /**
     * Constructs the SaleService with necessary dependencies.
     *
     * @param saleRepository       Repository for sale records
     * @param jwtService           JWT token service for user identification
     * @param userRepository       User repository for user info
     * @param customerRepository   Customer repository
     * @param drugRepository       Drug repository
     */
    @Autowired
    public SaleService(
            SaleRepository saleRepository,
            JwtService jwtService,
            IUserRepository userRepository,
            CustomerRepository customerRepository,
            DrugRepository drugRepository
    ) {
        this.saleRepository = saleRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.drugRepository = drugRepository;
    }

    /**
     * Retrieves all sales records with resolved customer and medication names.
     *
     * @return list of sale DTOs
     */
    @PreAuthorize("hasRole('ADMIN')")
    public List<GetSaleDto> getAll() {
        return saleRepository.findAll().stream()
                .map(sale -> {
                    String customerName = userRepository.findById(Long.valueOf(sale.getCustomerId()))
                            .map(UserEntity::getUsername)
                            .orElse("Unknown Customer");

                    String medicationName = drugRepository.findById(Long.valueOf(sale.getMedicationId()))
                            .map(drug -> drug.getName())
                            .orElse("Unknown Medication");

                    return new GetSaleDto(
                            sale.getId(),
                            customerName,
                            medicationName,
                            sale.getQuantity(),
                            sale.getTotalPrice(),
                            sale.getSaleDate()
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single sale record by ID.
     *
     * @param id ID of the sale
     * @return DTO of the sale
     * @throws RuntimeException if sale not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    public GetSaleDto getOne(long id) {
        var sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        String customerName = userRepository.findById(Long.valueOf(sale.getCustomerId()))
                .map(UserEntity::getUsername)
                .orElse("Unknown Customer");

        String medicationName = drugRepository.findById(Long.valueOf(sale.getMedicationId()))
                .map(drug -> drug.getName())
                .orElse("Unknown Medication");

        return new GetSaleDto(
                sale.getId(),
                customerName,
                medicationName,
                sale.getQuantity(),
                sale.getTotalPrice(),
                sale.getSaleDate()
        );
    }

    /**
     * Creates and saves a new sale record.
     *
     * @param saleDto DTO with sale input data
     * @return response DTO with persisted sale data
     */
    public CreateSaleResponseDto create(CreateSaleDto saleDto) {
        var now = Timestamp.from(Instant.now());

        var model = new SaleModel(
                null,
                saleDto.getCustomerId(),
                saleDto.getMedicationId(),
                saleDto.getQuantity(),
                saleDto.getTotalPrice(),
                now
        );

        var entity = new SalesEntity();
        entity.setCustomerId(model.getCustomerId());
        entity.setMedicationId(model.getMedicationId());
        entity.setQuantity(model.getQuantity());
        entity.setTotalPrice(model.getTotalPrice());
        entity.setSaleDate(model.getSaleDate());

        var saved = saleRepository.save(entity);

        return new CreateSaleResponseDto(
                saved.getId(),
                saved.getCustomerId(),
                saved.getMedicationId(),
                saved.getQuantity(),
                saved.getTotalPrice(),
                saved.getSaleDate()
        );
    }

    /**
     * Deletes a sale record by ID.
     *
     * @param id ID of the sale
     * @throws RuntimeException if sale does not exist
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(long id) {
        if (!saleRepository.existsById(id)) {
            throw new RuntimeException("Sale not found");
        }
        saleRepository.deleteById(id);
    }

    /**
     * Retrieves all sales made by the currently authenticated customer.
     *
     * @param token JWT token of the logged-in customer
     * @return list of sale DTOs
     */
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<GetSaleDto> getSalesForCurrentCustomer(String token) {
        String username = jwtService.getUsername(token);
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int customerId = customerRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Customer not found")).getId();

        return saleRepository.findByCustomerId(customerId).stream()
                .map(sale -> {
                    String medicationName = drugRepository.findById(Long.valueOf(sale.getMedicationId()))
                            .map(drug -> drug.getName())
                            .orElse("Unknown Medication");

                    return new GetSaleDto(
                            sale.getId(),
                            user.getUsername(),
                            medicationName,
                            sale.getQuantity(),
                            sale.getTotalPrice(),
                            sale.getSaleDate()
                    );
                })
                .collect(Collectors.toList());
    }
}

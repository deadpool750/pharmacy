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
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final JwtService jwtService;
    private final IUserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final DrugRepository drugRepository;

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

    public void delete(long id) {
        if (!saleRepository.existsById(id)) {
            throw new RuntimeException("Sale not found");
        }
        saleRepository.deleteById(id);
    }

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

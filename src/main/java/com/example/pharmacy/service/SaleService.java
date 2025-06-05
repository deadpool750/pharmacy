package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.sale.CreateSaleDto;
import com.example.pharmacy.controller.dto.sale.CreateSaleResponseDto;
import com.example.pharmacy.controller.dto.sale.GetSaleDto;
import com.example.pharmacy.infrastructure.entity.SalesEntity;
import com.example.pharmacy.repository.SaleRepository;
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

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<GetSaleDto> getAll() {
        return saleRepository.findAll().stream()
                .map(sale -> new GetSaleDto(
                        sale.getId(),
                        sale.getCustomerId(),
                        sale.getMedicationId(),
                        sale.getQuantity(),
                        sale.getTotalPrice(),
                        sale.getSaleDate()
                ))
                .collect(Collectors.toList());
    }

    public GetSaleDto getOne(long id) {
        var sale = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
        return new GetSaleDto(
                sale.getId(),
                sale.getCustomerId(),
                sale.getMedicationId(),
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
}

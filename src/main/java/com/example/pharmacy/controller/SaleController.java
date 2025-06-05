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

@RestController
@RequestMapping("/api/sales")
@PreAuthorize("isAuthenticated()")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<GetSaleDto> getAllSales() {
        return saleService.getAll();
    }

    @GetMapping("/{id}")
    public GetSaleDto getSale(@PathVariable long id) {
        return saleService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateSaleResponseDto> createSale(@RequestBody CreateSaleDto dto) {
        var sale = saleService.create(dto);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

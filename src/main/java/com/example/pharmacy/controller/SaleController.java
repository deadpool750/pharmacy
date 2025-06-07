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
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    /*@PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CreateSaleResponseDto> createSale(@RequestBody CreateSaleDto dto) {
        var sale = saleService.create(dto);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }
*/
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<GetSaleDto> getAllSales() {
        return saleService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public GetSaleDto getSale(@PathVariable long id) {
        return saleService.getOne(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSale(@PathVariable long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // NEW: Fetch logged-in customer's own sales
    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<GetSaleDto> getOwnSales(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Strip "Bearer "
        return saleService.getSalesForCurrentCustomer(token);
    }

}

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

@RestController
@RequestMapping("/api/suppliers")
@PreAuthorize("hasRole('ADMIN')")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<GetSupplierDto> getAllSuppliers() {
        return supplierService.getAll();
    }

    @GetMapping("/{id}")
    public GetSupplierDto getSupplier(@PathVariable long id) {
        return supplierService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateSupplierResponseDto> createSupplier(@RequestBody CreateSupplierDto dto) {
        var supplier = supplierService.create(dto);
        return new ResponseEntity<>(supplier, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetSupplierDto> updateSupplier(@PathVariable long id, @RequestBody CreateSupplierDto dto) {
        var updatedSupplier = supplierService.update(id, dto);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

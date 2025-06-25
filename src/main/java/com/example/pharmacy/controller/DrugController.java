package com.example.pharmacy.controller;

import com.example.pharmacy.controller.dto.drug.CreateDrugDto;
import com.example.pharmacy.controller.dto.drug.CreateDrugResponseDto;
import com.example.pharmacy.controller.dto.drug.GetDrugDto;
import com.example.pharmacy.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing medications (drugs).
 */
@RestController
@RequestMapping("/api/drugs")
public class DrugController {

    /** Service for handling drug-related operations. */
    private final DrugService drugService;

    /**
     * Constructs the DrugController with the provided DrugService.
     *
     * @param drugService the drug service
     */
    @Autowired
    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    /**
     * Retrieves a list of all available drugs.
     *
     * @return a list of GetDrugDto objects
     */
    @GetMapping
    @PreAuthorize("permitAll()")
    public List<GetDrugDto> getAllDrugs() {
        return drugService.getAll();
    }

    /**
     * Retrieves a specific drug by ID.
     *
     * @param id the ID of the drug
     * @return the requested drug details
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public GetDrugDto getOne(@PathVariable int id) {
        return drugService.getOne(id);
    }

    /**
     * Creates a new drug. Only accessible to admins.
     *
     * @param medications the drug data to create
     * @return the created drug details
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateDrugResponseDto> create(@RequestBody CreateDrugDto medications) {
        var newDrug = drugService.create(medications);
        return new ResponseEntity<>(newDrug, HttpStatus.CREATED);
    }

    /**
     * Deletes a drug by ID. Only accessible to admins.
     *
     * @param id the ID of the drug to delete
     * @return a 204 No Content response on success
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        drugService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing drug by ID.
     *
     * @param id the ID of the drug to update
     * @param dto the updated drug data
     * @return the updated drug details
     */
    @PutMapping("/{id}")
    public ResponseEntity<CreateDrugResponseDto> updateDrug(@PathVariable int id, @RequestBody CreateDrugDto dto) {
        return ResponseEntity.ok(drugService.updateDrug(id, dto));
    }
}

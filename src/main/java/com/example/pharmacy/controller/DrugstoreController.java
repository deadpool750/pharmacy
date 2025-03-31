package com.example.pharmacy.controller;

import com.example.pharmacy.controller.dto.CreateDrugDto;
import com.example.pharmacy.controller.dto.CreateDrugResponseDto;
import com.example.pharmacy.controller.dto.GetDrugDto;
import com.example.pharmacy.infrastructure.entity.MedicationsEntity;
import com.example.pharmacy.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugstoreController {

    private final DrugService drugService;

    @Autowired
    public DrugstoreController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping
    public List<GetDrugDto> getAllDrugs() {
        return drugService.getAll();
    }

    @GetMapping("/{id}")
    public GetDrugDto getOne(@PathVariable int id){
        return drugService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateDrugResponseDto> create(@RequestBody CreateDrugDto medications){
        var newDrug = drugService.create(medications);
        return new ResponseEntity<>(newDrug, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        drugService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

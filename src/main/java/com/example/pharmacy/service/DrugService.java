package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.CreateDrugDto;
import com.example.pharmacy.controller.dto.CreateDrugResponseDto;
import com.example.pharmacy.controller.dto.GetDrugDto;
import com.example.pharmacy.infrastructure.entity.MedicationsEntity;
import com.example.pharmacy.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugService {
    private final DrugRepository drugRepository;

    @Autowired
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public List<GetDrugDto> getAll(){
        var medications = drugRepository.findAll();
        return medications.stream()
                .map(medication -> new GetDrugDto(
                        medication.getId(),
                        medication.getName(),
                        medication.getManufacturer(),
                        medication.getPrice(),
                        medication.getExpirationDate(),
                        medication.getStockQuantity() > 0
                ))
                .collect(Collectors.toList());
    }

    public GetDrugDto getOne(int id){
        var medication = drugRepository.findById((long) id).orElseThrow( () -> new RuntimeException("Medication not found"));
        return new GetDrugDto(
                medication.getId(),
                medication.getName(),
                medication.getManufacturer(),
                medication.getPrice(),
                medication.getExpirationDate(),
                medication.getStockQuantity() > 0
        );
    }

    public CreateDrugResponseDto create(CreateDrugDto medication){
        var drugEntity = new MedicationsEntity();
        drugEntity.setName(medication.getName());
        drugEntity.setManufacturer(medication.getManufacturer());
        drugEntity.setPrice(medication.getPrice());
        drugEntity.setStockQuantity(medication.getStockQuantity());
        drugEntity.setExpirationDate(medication.getExpirationDate());

        var newDrug = drugRepository.save(drugEntity);

        return new CreateDrugResponseDto(newDrug.getId(), newDrug.getName() ,newDrug.getManufacturer(), newDrug.getPrice(), newDrug.getExpirationDate(), newDrug.getStockQuantity());
    }

    public void delete(long id){
        if (!drugRepository.existsById(id)){
            throw new RuntimeException();
        }
        drugRepository.deleteById(id);
    }
}

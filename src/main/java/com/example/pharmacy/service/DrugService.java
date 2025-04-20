package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.drug.CreateDrugDto;
import com.example.pharmacy.controller.dto.drug.CreateDrugResponseDto;
import com.example.pharmacy.controller.dto.drug.GetDrugDto;
import com.example.pharmacy.infrastructure.entity.MedicationsEntity;
import com.example.pharmacy.repository.DrugRepository;
import com.example.pharmacy.service.inputs.DrugModel;
import com.example.pharmacy.service.valueObjects.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public CreateDrugResponseDto create(CreateDrugDto medication) {
        // Convert BigDecimal from DTO to float (safely), then to Price value object
        BigDecimal priceValue = BigDecimal.valueOf(medication.getPrice().floatValue());
        var price = Price.create(priceValue);

        var drugModel = new DrugModel(
                null,
                medication.getName(),
                medication.getManufacturer(),
                price,
                medication.getExpirationDate(),
                medication.getStockQuantity()
        );

        // Convert model to entity
        var drugEntity = new MedicationsEntity();
        drugEntity.setName(drugModel.getName());
        drugEntity.setManufacturer(drugModel.getManufacturer());
        drugEntity.setPrice(drugModel.getPrice()); // Extract Float value
        drugEntity.setStockQuantity(drugModel.getStockQuantity());
        drugEntity.setExpirationDate(drugModel.getExpirationDate());

        var newDrug = drugRepository.save(drugEntity);

        return new CreateDrugResponseDto(
                newDrug.getId(),
                newDrug.getName(),
                newDrug.getManufacturer(),
                newDrug.getPrice(),
                newDrug.getExpirationDate(),
                newDrug.getStockQuantity()
        );
    }

    public void delete(long id){
        if (!drugRepository.existsById(id)){
            throw new RuntimeException();
        }
        drugRepository.deleteById(id);
    }
}

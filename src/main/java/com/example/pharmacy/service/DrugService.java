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

/**
 * Service class for managing drug-related operations, such as
 * retrieving, creating, updating, and deleting medications.
 */
@Service
public class DrugService {
    private final DrugRepository drugRepository;

    /**
     * Constructor to inject DrugRepository.
     *
     * @param drugRepository repository for accessing drug data
     */
    @Autowired
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    /**
     * Retrieves all medications from the database.
     *
     * @return a list of GetDrugDto representing all drugs
     */
    public List<GetDrugDto> getAll(){
        var medications = drugRepository.findAll();
        return medications.stream()
                .map(medication -> new GetDrugDto(
                        medication.getId(),
                        medication.getName(),
                        medication.getManufacturer(),
                        medication.getPrice(),
                        medication.getExpirationDate(),
                        medication.getStockQuantity() > 0,
                        medication.getStockQuantity()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single medication by ID.
     *
     * @param id the ID of the medication
     * @return the medication as a GetDrugDto
     * @throws RuntimeException if the medication is not found
     */
    public GetDrugDto getOne(int id){
        var medication = drugRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Medication not found"));
        return new GetDrugDto(
                medication.getId(),
                medication.getName(),
                medication.getManufacturer(),
                medication.getPrice(),
                medication.getExpirationDate(),
                medication.getStockQuantity() > 0,
                medication.getStockQuantity()
        );
    }

    /**
     * Creates a new drug based on input DTO.
     *
     * @param medication DTO containing drug creation data
     * @return a DTO representing the created drug
     */
    public CreateDrugResponseDto create(CreateDrugDto medication) {
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

        var drugEntity = new MedicationsEntity();
        drugEntity.setName(drugModel.getName());
        drugEntity.setManufacturer(drugModel.getManufacturer());
        drugEntity.setPrice(drugModel.getPrice());
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

    /**
     * Deletes a drug by its ID.
     *
     * @param id ID of the drug to delete
     * @throws RuntimeException if the drug does not exist
     */
    public void delete(long id){
        if (!drugRepository.existsById(id)){
            throw new RuntimeException();
        }
        drugRepository.deleteById(id);
    }

    /**
     * Updates an existing drug with new data.
     *
     * @param id the ID of the drug to update
     * @param dto the new drug data
     * @return a DTO representing the updated drug
     * @throws RuntimeException if the drug is not found
     */
    public CreateDrugResponseDto updateDrug(int id, CreateDrugDto dto) {
        MedicationsEntity drug = drugRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Drug not found"));

        drug.setName(dto.getName());
        drug.setManufacturer(dto.getManufacturer());
        drug.setPrice(dto.getPrice());
        drug.setExpirationDate(dto.getExpirationDate());
        drug.setStockQuantity(dto.getStockQuantity());

        drugRepository.save(drug);

        return new CreateDrugResponseDto(
                drug.getId(),
                drug.getName(),
                drug.getManufacturer(),
                drug.getPrice(),
                drug.getExpirationDate(),
                drug.getStockQuantity()
        );
    }

}

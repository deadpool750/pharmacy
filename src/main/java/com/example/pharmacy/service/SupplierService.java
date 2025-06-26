package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.suppliers.CreateSupplierDto;
import com.example.pharmacy.controller.dto.suppliers.CreateSupplierResponseDto;
import com.example.pharmacy.controller.dto.suppliers.GetSupplierDto;
import com.example.pharmacy.infrastructure.entity.SuppliersEntity;
import com.example.pharmacy.repository.SupplierRepository;
import com.example.pharmacy.service.inputs.SupplierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing supplier-related operations.
 */
@Service
public class SupplierService {

    private final SupplierRepository repository;

    /**
     * Constructs the SupplierService with required repository.
     *
     * @param repository the SupplierRepository bean
     */
    @Autowired
    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all suppliers from the database.
     *
     * @return list of GetSupplierDto
     */
    @PreAuthorize("hasRole('ADMIN')")
    public List<GetSupplierDto> getAll() {
        return repository.findAll().stream()
                .map(s -> new GetSupplierDto(s.getId(), s.getName(), s.getPhone(), s.getEmail(), s.getAddress()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific supplier by ID.
     *
     * @param id the supplier ID
     * @return the corresponding GetSupplierDto
     * @throws RuntimeException if supplier is not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    public GetSupplierDto getOne(long id) {
        var s = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        return new GetSupplierDto(s.getId(), s.getName(), s.getPhone(), s.getEmail(), s.getAddress());
    }

    /**
     * Creates a new supplier in the database.
     *
     * @param dto the CreateSupplierDto containing input data
     * @return CreateSupplierResponseDto containing saved data
     */
    @PreAuthorize("hasRole('ADMIN')")
    public CreateSupplierResponseDto create(CreateSupplierDto dto) {
        var model = new SupplierModel(null, dto.getName(), dto.getPhone(), dto.getEmail(), dto.getAddress());

        var entity = new SuppliersEntity();
        entity.setName(model.getName());
        entity.setPhone(model.getPhone());
        entity.setEmail(model.getEmail());
        entity.setAddress(model.getAddress());

        var saved = repository.save(entity);

        return new CreateSupplierResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getPhone(),
                saved.getEmail(),
                saved.getAddress()
        );
    }

    /**
     * Updates an existing supplier's information.
     *
     * @param id  the ID of the supplier to update
     * @param dto the new data to apply
     * @return updated GetSupplierDto
     * @throws RuntimeException if supplier is not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    public GetSupplierDto update(long id, CreateSupplierDto dto) {
        var supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setName(dto.getName());
        supplier.setPhone(dto.getPhone());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());

        var updated = repository.save(supplier);

        return new GetSupplierDto(
                updated.getId(),
                updated.getName(),
                updated.getPhone(),
                updated.getEmail(),
                updated.getAddress()
        );
    }

    /**
     * Deletes a supplier by ID.
     *
     * @param id the ID of the supplier to delete
     * @throws RuntimeException if the supplier is not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Supplier not found");
        }
        repository.deleteById(id);
    }
}

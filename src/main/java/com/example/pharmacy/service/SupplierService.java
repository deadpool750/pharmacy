package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.suppliers.CreateSupplierDto;
import com.example.pharmacy.controller.dto.suppliers.CreateSupplierResponseDto;
import com.example.pharmacy.controller.dto.suppliers.GetSupplierDto;
import com.example.pharmacy.infrastructure.entity.SuppliersEntity;
import com.example.pharmacy.repository.SupplierRepository;
import com.example.pharmacy.service.inputs.SupplierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository repository;

    @Autowired
    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    public List<GetSupplierDto> getAll() {
        return repository.findAll().stream()
                .map(s -> new GetSupplierDto(s.getId(), s.getName(), s.getPhone(), s.getEmail(), s.getAddress()))
                .collect(Collectors.toList());
    }

    public GetSupplierDto getOne(long id) {
        var s = repository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
        return new GetSupplierDto(s.getId(), s.getName(), s.getPhone(), s.getEmail(), s.getAddress());
    }

    public CreateSupplierResponseDto create(CreateSupplierDto dto) {
        var model = new SupplierModel(null, dto.getName(), dto.getPhone(), dto.getEmail(), dto.getAddress());

        var entity = new SuppliersEntity();
        entity.setName(model.getName());
        entity.setPhone(model.getPhone());
        entity.setEmail(model.getEmail());
        entity.setAddress(model.getAddress());

        var saved = repository.save(entity);

        return new CreateSupplierResponseDto(saved.getId(), saved.getName(), saved.getPhone(), saved.getEmail(), saved.getAddress());
    }

    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Supplier not found");
        }
        repository.deleteById(id);
    }
}

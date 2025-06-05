package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.employees.CreateEmployeeDto;
import com.example.pharmacy.controller.dto.employees.CreateEmployeeResponseDto;
import com.example.pharmacy.controller.dto.employees.GetEmployeeDto;
import com.example.pharmacy.infrastructure.entity.EmployeesEntity;
import com.example.pharmacy.repository.EmployeeRepository;
import com.example.pharmacy.service.inputs.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<GetEmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(emp -> new GetEmployeeDto(
                        emp.getId(),
                        emp.getName(),
                        emp.getPosition(),
                        emp.getSalary(),
                        emp.getHireDate()))
                .collect(Collectors.toList());
    }

    public GetEmployeeDto getOne(long id) {
        var emp = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        return new GetEmployeeDto(emp.getId(), emp.getName(), emp.getPosition(), emp.getSalary(), emp.getHireDate());
    }

    public CreateEmployeeResponseDto create(CreateEmployeeDto dto) {
        var model = new EmployeeModel(
                null,
                dto.getName(),
                dto.getPosition(),
                dto.getSalary(),
                dto.getHireDate()
        );

        var entity = new EmployeesEntity();
        entity.setName(model.getName());
        entity.setPosition(model.getPosition());
        entity.setSalary(model.getSalary());
        entity.setHireDate(model.getHireDate());

        var saved = employeeRepository.save(entity);

        return new CreateEmployeeResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getPosition(),
                saved.getSalary(),
                saved.getHireDate()
        );
    }

    public void delete(long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}

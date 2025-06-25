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

/**
 * Service layer responsible for business logic related to employees.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Constructs an EmployeeService with the given repository.
     *
     * @param employeeRepository the repository used to interact with the employee database
     */
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieves all employees from the database.
     *
     * @return a list of employee DTOs
     */
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

    /**
     * Retrieves a single employee by ID.
     *
     * @param id the ID of the employee
     * @return the corresponding employee DTO
     * @throws RuntimeException if the employee is not found
     */
    public GetEmployeeDto getOne(long id) {
        var emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return new GetEmployeeDto(
                emp.getId(),
                emp.getName(),
                emp.getPosition(),
                emp.getSalary(),
                emp.getHireDate()
        );
    }

    /**
     * Creates a new employee in the system.
     *
     * @param dto the data for the new employee
     * @return the response DTO of the created employee
     */
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

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @throws RuntimeException if the employee does not exist
     */
    public void delete(long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    /**
     * Updates an existing employee's data.
     *
     * @param id  the ID of the employee to update
     * @param dto the new data to apply
     * @return the updated employee DTO
     * @throws RuntimeException if the employee is not found
     */
    public GetEmployeeDto update(long id, CreateEmployeeDto dto) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(dto.getName());
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        employee.setHireDate(dto.getHireDate());

        var saved = employeeRepository.save(employee);

        return new GetEmployeeDto(
                saved.getId(),
                saved.getName(),
                saved.getPosition(),
                saved.getSalary(),
                saved.getHireDate()
        );
    }
}

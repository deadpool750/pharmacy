package com.example.pharmacy.controller;

import com.example.pharmacy.controller.dto.employees.CreateEmployeeDto;
import com.example.pharmacy.controller.dto.employees.CreateEmployeeResponseDto;
import com.example.pharmacy.controller.dto.employees.GetEmployeeDto;
import com.example.pharmacy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing employee-related operations.
 * Accessible only by users with ADMIN role.
 */
@RestController
@RequestMapping("/api/employees")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {

    /** Service for handling employee-related operations. */
    private final EmployeeService employeeService;

    /**
     * Constructs the EmployeeController with the given service.
     *
     * @param employeeService the employee service
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves all employees.
     *
     * @return a list of employee DTOs
     */
    @GetMapping
    public List<GetEmployeeDto> getAllEmployees() {
        return employeeService.getAll();
    }

    /**
     * Retrieves a specific employee by ID.
     *
     * @param id the ID of the employee
     * @return the employee DTO
     */
    @GetMapping("/{id}")
    public GetEmployeeDto getEmployee(@PathVariable long id) {
        return employeeService.getOne(id);
    }

    /**
     * Creates a new employee.
     *
     * @param dto the employee data to create
     * @return the created employee DTO with status 201
     */
    @PostMapping
    public ResponseEntity<CreateEmployeeResponseDto> createEmployee(@RequestBody CreateEmployeeDto dto) {
        var newEmp = employeeService.create(dto);
        return new ResponseEntity<>(newEmp, HttpStatus.CREATED);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @return a 204 No Content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing employee by ID.
     *
     * @param id the ID of the employee
     * @param dto the new employee data
     * @return the updated employee DTO with status 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<GetEmployeeDto> updateEmployee(@PathVariable long id, @RequestBody CreateEmployeeDto dto) {
        var updatedEmployee = employeeService.update(id, dto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}

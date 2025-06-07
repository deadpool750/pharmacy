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

@RestController
@RequestMapping("/api/employees")
@PreAuthorize("hasRole('ADMIN')")

public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<GetEmployeeDto> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public GetEmployeeDto getEmployee(@PathVariable long id) {
        return employeeService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateEmployeeResponseDto> createEmployee(@RequestBody CreateEmployeeDto dto) {
        var newEmp = employeeService.create(dto);
        return new ResponseEntity<>(newEmp, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.pharmacy.controller.dto.employees;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;

public class CreateEmployeeDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Position is required")
    private String position;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Salary must be greater than 0")
    private BigDecimal salary;

    @NotNull(message = "Hire date is required")
    private Date hireDate;

    public CreateEmployeeDto() {}

    public CreateEmployeeDto(String name, String position, BigDecimal salary, Date hireDate) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }


}

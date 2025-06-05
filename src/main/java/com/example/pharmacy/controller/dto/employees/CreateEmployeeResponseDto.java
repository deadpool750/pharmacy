package com.example.pharmacy.controller.dto.employees;

import java.math.BigDecimal;
import java.sql.Date;

public class CreateEmployeeResponseDto {

    private int id;
    private String name;
    private String position;
    private BigDecimal salary;
    private Date hireDate;

    public CreateEmployeeResponseDto() {}

    public CreateEmployeeResponseDto(int id, String name, String position, BigDecimal salary, Date hireDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

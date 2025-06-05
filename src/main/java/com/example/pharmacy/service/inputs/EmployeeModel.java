package com.example.pharmacy.service.inputs;

import java.math.BigDecimal;
import java.sql.Date;

public class EmployeeModel {
    private Long id;
    private String name;
    private String position;
    private BigDecimal salary;
    private Date hireDate;

    public EmployeeModel(Long id, String name, String position, BigDecimal salary, Date hireDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Date getHireDate() {
        return hireDate;
    }
}

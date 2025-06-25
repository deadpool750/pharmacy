package com.example.pharmacy.controller.dto.employees;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data Transfer Object for creating a new employee.
 * Includes validation for name, position, salary, and hire date.
 */
public class CreateEmployeeDto {

    /** Employee's full name. Cannot be blank. */
    @NotBlank(message = "Name is required")
    private String name;

    /** Employee's job position. Cannot be blank. */
    @NotBlank(message = "Position is required")
    private String position;

    /**
     * Employee's salary. Must be greater than 0.
     */
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Salary must be greater than 0")
    private BigDecimal salary;

    /** Employee's hire date. Cannot be null. */
    @NotNull(message = "Hire date is required")
    private Date hireDate;

    /** Default constructor. */
    public CreateEmployeeDto() {}

    /**
     * Constructs a CreateEmployeeDto with all fields.
     *
     * @param name the employee's name
     * @param position the employee's position
     * @param salary the employee's salary
     * @param hireDate the employee's hire date
     */
    public CreateEmployeeDto(String name, String position, BigDecimal salary, Date hireDate) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    /**
     * Gets the employee's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the employee's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the employee's position.
     *
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the employee's position.
     *
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets the employee's salary.
     *
     * @return the salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Sets the employee's salary.
     *
     * @param salary the salary to set
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Gets the employee's hire date.
     *
     * @return the hire date
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * Sets the employee's hire date.
     *
     * @param hireDate the hire date to set
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}

package com.example.pharmacy.controller.dto.employees;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Data Transfer Object for retrieving employee information.
 */
public class GetEmployeeDto {

    /** Unique identifier of the employee. */
    private int id;

    /** Full name of the employee. */
    private String name;

    /** Job position of the employee. */
    private String position;

    /** Salary of the employee. */
    private BigDecimal salary;

    /** Hire date of the employee. */
    private Date hireDate;

    /** Default constructor. */
    public GetEmployeeDto() {}

    /**
     * Constructs a GetEmployeeDto with all fields.
     *
     * @param id the employee ID
     * @param name the employee name
     * @param position the job position
     * @param salary the salary
     * @param hireDate the hire date
     */
    public GetEmployeeDto(int id, String name, String position, BigDecimal salary, Date hireDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    /**
     * Gets the employee ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the employee ID.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
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

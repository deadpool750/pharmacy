package com.example.pharmacy.service.inputs;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Model representing internal employee data used within the service layer.
 */
public class EmployeeModel {

    /** Unique identifier of the employee. */
    private Long id;

    /** Full name of the employee. */
    private String name;

    /** Job position/title of the employee. */
    private String position;

    /** Salary of the employee. */
    private BigDecimal salary;

    /** Hire date of the employee. */
    private Date hireDate;

    /**
     * Constructs an EmployeeModel with all fields.
     *
     * @param id the employee ID
     * @param name the employee name
     * @param position the job position
     * @param salary the salary
     * @param hireDate the hire date
     */
    public EmployeeModel(Long id, String name, String position, BigDecimal salary, Date hireDate) {
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
    public Long getId() {
        return id;
    }

    /**
     * Gets the employee name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the employee's job position.
     *
     * @return the position
     */
    public String getPosition() {
        return position;
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
     * Gets the employee's hire date.
     *
     * @return the hire date
     */
    public Date getHireDate() {
        return hireDate;
    }
}

package com.example.pharmacy.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 * Entity representing an employee in the pharmacy system.
 */
@Entity
@Table(name = "employees", schema = "drugstore_db", catalog = "")
public class EmployeesEntity {

    /** Unique identifier for the employee (primary key). */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    /** Name of the employee. */
    @Basic
    @Column(name = "name")
    private String name;

    /** Job position of the employee. */
    @Basic
    @Column(name = "position")
    private String position;

    /** Salary of the employee. */
    @Basic
    @Column(name = "salary")
    private BigDecimal salary;

    /** Date the employee was hired. */
    @Basic
    @Column(name = "hire_date")
    private Date hireDate;

    /**
     * Gets the employee ID.
     *
     * @return the employee ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the employee ID.
     *
     * @param id the employee ID
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
     * @param name the name
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
     * @param position the position
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
     * @param salary the salary
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Gets the hire date of the employee.
     *
     * @return the hire date
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * Sets the hire date of the employee.
     *
     * @param hireDate the hire date
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Checks equality based on all employee fields.
     *
     * @param o the object to compare
     * @return true if objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeesEntity that = (EmployeesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(position, that.position) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(hireDate, that.hireDate);
    }

    /**
     * Generates hash code based on employee fields.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, position, salary, hireDate);
    }
}

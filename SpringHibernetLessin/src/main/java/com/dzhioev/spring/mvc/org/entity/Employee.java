package com.dzhioev.spring.mvc.org.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table (name = "employees")
public class Employee {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    @Column (name = "name")
    @Size (min=3, message = "Name is wrong")
    private String name;
    @Column (name = "surname")
    @NotBlank(message = "Surname is required field")
    private String surname;
    @Column (name = "department")
    @NotEmpty (message = "Department is required field")
    private String department;
    @Column (name = "salary")
    @Min(200)
    private int salary;

    public Employee() {
    }

    public Employee(int id, String name, String surname, String department, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

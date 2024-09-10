package com.dzhioev.pavel;

import com.dzhioev.pavel.validation.CheckEmail;
import org.springframework.expression.spel.ast.LongLiteral;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.Map;

public class Employee {
    @Size(min = 2, max = 100, message = "Name too small")
    private String name;
//  @NotEmpty(message = "Surname is required field")
    @NotBlank(message = "Surname is required field")
    private String surname;
    @Min(value = 500, message = "must be greater 499")
    @Max(value = 2000, message = "Too big")
    private int salary;
    private String department;
    private Map<String, String> departments;
    private String carBrand;
    private Map<String, String> carBrands;
    private String[] languages;
    private Map<String, String> languageList;
    @Pattern(regexp = "\\d{3}-\\d{2}-\\d{2}", message = "Phone number is wrong")
    private String phoneNumber;
    @CheckEmail
    private String email;

    public Employee() {
        departments = new HashMap<>();
        departments.put ("IT", "Information Tech");
        departments.put ("HR", "Human Resourses");
        departments.put ("Sales", "Sales");
        carBrands = new HashMap<>();
        carBrands.put ("Mercedes", "Mercedes");
        carBrands.put ("Nissan", "Nissan");
        carBrands.put ("Audi", "Audi");
        languageList = new HashMap<>();
        languageList.put("English", "EN");
        languageList.put("Russian", "RU");
        languageList.put("Chines", "CH");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String[] getLanguages() {
        return languages;
    }

    public Map<String, String> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(Map<String, String> languageList) {
        this.languageList = languageList;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Map<String, String> getDepartments() {
        return departments;
    }

    public void setDepartments(Map<String, String> departments) {
        this.departments = departments;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Map<String, String> getCarBrands() {
        return carBrands;
    }

    public void setCarBrands(Map<String, String> carBrands) {
        this.carBrands = carBrands;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}

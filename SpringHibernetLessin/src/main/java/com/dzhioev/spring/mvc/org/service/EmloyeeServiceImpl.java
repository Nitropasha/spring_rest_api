package com.dzhioev.spring.mvc.org.service;

import com.dzhioev.spring.mvc.org.dao.EmployeeDAO;
import com.dzhioev.spring.mvc.org.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EmloyeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
         employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
         employeeDAO.deleteEmployee(id);
    }
}

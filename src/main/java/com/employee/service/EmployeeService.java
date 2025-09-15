package com.employee.service;

import com.employee.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    void addEmployee(Employee e);

    Optional<Employee> getEmployee(Long id);

    List<Employee> getAllEmployees();

    Optional<Employee> updateEmployee(Long id, Employee e);

    boolean deleteEmployee(Long id);
    Optional<Employee> updateEmployeeDetails(Long id, Map<String, Object> updates);
}

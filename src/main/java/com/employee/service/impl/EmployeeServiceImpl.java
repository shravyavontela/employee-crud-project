package com.employee.service.impl;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(()
                -> new EmployeeNotFoundException(id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public Optional<Employee> updateEmployee(Long id, Employee e) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setName(e.getName());
            existingEmployee.setAge(e.getAge());
            existingEmployee.setEmail(e.getEmail());
         return employeeRepository.save(existingEmployee);
        });
    }

    @Override
    public Optional<Employee> updateEmployeeDetails(Long id, Map<String, Object> updates) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new EmployeeNotFoundException(id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" :
                    employee.setName((String) value);
                    break;
                case "age" :
                    employee.setAge((Integer) value);
                    break;
                case "email" :
                    employee.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field "+key+" not valid");
            }
        });
        return Optional.of(employeeRepository.save(employee));
    }
}

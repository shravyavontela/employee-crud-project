package com.employee.controller;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/crud/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        return Optional.of(employeeService.getEmployee(id)).map(ResponseEntity::ok)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted)
            return ResponseEntity.ok("Deleted Employee with id: " + id);
        else
            throw new EmployeeNotFoundException(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            return employeeService.updateEmployee(id, employee).map(ResponseEntity::ok)
                    .orElseThrow(() -> new EmployeeNotFoundException(id));
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable Long id,
                                                   @RequestBody Map<String, Object> updates) {
        try {
            Employee updatedEmployee = employeeService.updateEmployeeDetails(id, updates).get();
            return ResponseEntity.ok(updatedEmployee);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}




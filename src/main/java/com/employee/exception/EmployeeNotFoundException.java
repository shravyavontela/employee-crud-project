package com.employee.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private Long id;
    public EmployeeNotFoundException(Long id) {
        super("Employee not found with ID: " + id);
    }
}

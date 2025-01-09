package com.assignment.employeeservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {
    
    /**
     * Fetch all employees with pagination
     * 
     * @param pageable
     * @return
     */
    Page<EmployeeDTO> getAllEmployees(Pageable pageable);

    /**
     * Fetch a single employee by ID
     * 
     * @param id
     * @return
     */
    EmployeeDTO getEmployeeById(Long id);

    
    /**
     * Create a new employee
     * 
     * @param employeeDTO
     * @return
     */
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    /**
     * Update an existing employee by ID
     * 
     * @param id
     * @param employeeDTO
     * @return
     */
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    // Delete an employee by ID
    
    /**
     * Delete an employee by ID
     * 
     * @param id
     */
    void deleteEmployee(Long id);
}


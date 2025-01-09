package com.assignment.employeeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.employeeservice.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// Check if an employee exists with the same name and pin code
	Optional<Employee> findByNameAndPinCode(String name, String pinCode);

}

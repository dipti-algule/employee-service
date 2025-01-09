package com.assignment.employeeservice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.employeeservice.dto.EmployeeDTO;
import com.assignment.employeeservice.exception.EmployeeNotFoundException;
import com.assignment.employeeservice.mapper.EmployeeMapper;
import com.assignment.employeeservice.model.Employee;
import com.assignment.employeeservice.model.Employee.Gender;
import com.assignment.employeeservice.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Page<EmployeeDTO> getAllEmployees(Pageable pageable) {
		logger.info("Fetching all employees with pagination: {}", pageable);
		return employeeRepository.findAll(pageable).map(EmployeeMapper.INSTANCE::employeeToEmployeeDTO);
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		logger.info("Fetching employee with ID: {}", id);
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
		return EmployeeMapper.INSTANCE.employeeToEmployeeDTO(employee);
	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		logger.info("Creating a new employee: {}", employeeDTO);
		
		// Check if employee with the same name and pinCode already exists
        Optional<Employee> existingEmployee = employeeRepository.findByNameAndPinCode(employeeDTO.getName(), employeeDTO.getPinCode());

        if (existingEmployee.isPresent()) {
            throw new IllegalArgumentException("Employee with the same name and pin code already exists.");
        }
        
		Employee employee = EmployeeMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.INSTANCE.employeeToEmployeeDTO(savedEmployee);
	}

	@Override
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
		logger.info("Updating employee with ID: {}", id);
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

		// Update fields
		existingEmployee.setName(employeeDTO.getName());
		existingEmployee.setAge(employeeDTO.getAge());
		existingEmployee.setGender(Gender.valueOf(employeeDTO.getGender().toUpperCase()));
		existingEmployee.setCity(employeeDTO.getCity());
		existingEmployee.setPinCode(employeeDTO.getPinCode());

		Employee updatedEmployee = employeeRepository.save(existingEmployee);
		return EmployeeMapper.INSTANCE.employeeToEmployeeDTO(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Long id) {
		logger.info("Deleting employee with ID: {}", id);
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
		employeeRepository.delete(existingEmployee);
		logger.info("Employee with ID {} deleted successfully", id);

	}

}

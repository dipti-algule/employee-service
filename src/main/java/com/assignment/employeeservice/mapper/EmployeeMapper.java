package com.assignment.employeeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.assignment.employeeservice.dto.EmployeeDTO;
import com.assignment.employeeservice.model.Employee;

@Mapper
public interface EmployeeMapper {
    
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    // Mapping from Entity to DTO
    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    // Mapping from DTO to Entity
    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);
}


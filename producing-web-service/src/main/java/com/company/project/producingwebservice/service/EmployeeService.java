package com.company.service;

import com.company.dto.CreateEmployee;
import com.company.dto.CreateEmployeeDTO;
import com.company.dto.EmployeeDTO;
import com.company.dto.FindAllEmployeesDTO;
import com.company.entity.Employee;

import java.util.List;

public interface EmployeeService {

     public List<Employee> findAllEmployee(FindAllEmployeesDTO findAllEmployeesDTO);

     public Employee findOne(Long id);

     public EmployeeDTO createEmployee(CreateEmployee createEmployeeDTO);

     public EmployeeDTO deleteEmployee(Long id);

     public EmployeeDTO updateEmployee(CreateEmployeeDTO createEmployeeDTO);

     public EmployeeDTO employeeToDTO(Employee emp);

}

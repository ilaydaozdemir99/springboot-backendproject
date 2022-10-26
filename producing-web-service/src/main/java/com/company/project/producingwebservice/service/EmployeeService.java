package com.company.project.producingwebservice.service;


import com.company.project.producingwebservice.dto.*;
import com.company.project.producingwebservice.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

     public List<Employee> findAllEmployee(FindAllEmployeesDTO findAllEmployeesDTO);

     public Employee findOne(Long id);

     public EmployeeDTO createEmployee(CreateEmployee createEmployeeDTO);

     public EmployeeDTO deleteEmployee(Long id);

     public EmployeeDTO updateEmployee(CreateEmployeeDTO createEmployeeDTO);

     public EmployeeDTO employeeToDTO(Employee emp);

     public List<EmployeeQueryDTO> findAllByQuery();

     public List<EmployeeQueryDTO> findByName(EmployeeQueryDTO employeeQueryDTO, FindAllEmployeesDTO findAllEmployeesDTO);

}

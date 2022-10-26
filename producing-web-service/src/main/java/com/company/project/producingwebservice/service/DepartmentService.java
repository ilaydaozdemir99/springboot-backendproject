package com.company.project.producingwebservice.service;


import com.company.project.producingwebservice.dto.CreateDepartmentDTO;
import com.company.project.producingwebservice.dto.DepartmentDTO;
import com.company.project.producingwebservice.dto.FindAllDepartmentsDTO;
import com.company.project.producingwebservice.entity.Department;
import com.company.project.producingwebservice.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    public List<Department> findAllDepartment(FindAllDepartmentsDTO findAllDepartmentsDTO);

    public Department findOne(Long id);

    public DepartmentDTO createDepartment(CreateDepartmentDTO createDepartmentDTO);

    public DepartmentDTO deleteDepartment(Long id);

    public DepartmentDTO updateDepartment(CreateDepartmentDTO createDepartmentDTO);

    public Department addEmployee(Employee emp, Long departmentId);

    public Department deleteEmployee(Employee emp, Department dept);

    public List<Employee> findEmployees(Long id);

    public DepartmentDTO departmentToDTO(Department dept);
}

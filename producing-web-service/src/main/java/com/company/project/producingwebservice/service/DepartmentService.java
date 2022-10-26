package com.company.service;

import com.company.dto.CreateDepartmentDTO;
import com.company.dto.DepartmentDTO;
import com.company.dto.FindAllDepartmentsDTO;
import com.company.entity.Department;
import com.company.entity.Employee;

import java.util.List;

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

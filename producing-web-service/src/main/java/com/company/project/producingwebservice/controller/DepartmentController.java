package com.company.controller;


import com.company.dto.CreateDepartmentDTO;
import com.company.dto.DepartmentDTO;
import com.company.dto.EmployeeDTO;
import com.company.dto.FindAllDepartmentsDTO;
import com.company.entity.Department;
import com.company.entity.Employee;
import com.company.service.DepartmentService;
import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public DepartmentDTO createDepartment(@RequestBody CreateDepartmentDTO createDepartmentDTO) {
        return departmentService.createDepartment(createDepartmentDTO);
    }

    @GetMapping
    public List<Department> findAllDepartment(@RequestBody FindAllDepartmentsDTO findAllDepartmentsDTO) {
        return departmentService.findAllDepartment(findAllDepartmentsDTO);
    }

    @GetMapping("/{departmentId}")
    public DepartmentDTO findOne(@PathVariable("departmentId") Long id) {
        Department department = departmentService.findOne(id);
        return departmentService.departmentToDTO(department);
    }

    @PutMapping
    public DepartmentDTO updateDepartment(@RequestBody CreateDepartmentDTO createDepartmentDTO) {
        return departmentService.updateDepartment(createDepartmentDTO);
    }

    @DeleteMapping("/{departmentId}")
    public DepartmentDTO deleteDepartment(@PathVariable("departmentId") Long id) {
        return departmentService.deleteDepartment(id);
    }

    @GetMapping("/{departmentId}/employees")
    public List<EmployeeDTO> findEmployees(@PathVariable("departmentId") Long id) {
        return departmentService.findEmployees(id).stream().map(employee -> employeeService.employeeToDTO(employee)).collect(Collectors.toList());
    }

}










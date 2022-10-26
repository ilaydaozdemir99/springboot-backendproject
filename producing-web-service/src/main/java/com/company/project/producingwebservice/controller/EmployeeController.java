package com.company.project.producingwebservice.controller;


import com.company.project.producingwebservice.dto.*;
import com.company.project.producingwebservice.entity.Employee;
import com.company.project.producingwebservice.service.EmployeeService;
import com.company.project.producingwebservice.wrapper.EmployeeRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody CreateEmployee test) {
        return employeeService.createEmployee(test);
    }

    @GetMapping
    public List<Employee> findAllEmployee(@RequestBody FindAllEmployeesDTO findAllEmployeesDTO) {
        //List<Employee> employees =  employeeService.findAllEmployee();
       // return employees.stream().map(employee -> employeeService.employeeToDTO(employee)).collect(Collectors.toList());
        return employeeService.findAllEmployee(findAllEmployeesDTO);
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO findOne(@PathVariable("employeeId") Long id) {
        Employee employee = employeeService.findOne(id);
        return employeeService.employeeToDTO(employee);
    }

    @PutMapping
    public EmployeeDTO updateEmployee( @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return employeeService.updateEmployee(createEmployeeDTO);
    }

    @DeleteMapping("/{employeeId}")
    public EmployeeDTO deleteEmployee(@PathVariable("employeeId") Long id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/findAllByQuery")
    public List<EmployeeQueryDTO> findAllEmployeeByQuery() {
        return employeeService.findAllByQuery();
    }

    @GetMapping("/findAllByName")
    public List<EmployeeQueryDTO> findAllByName(@RequestBody EmployeeRequestWrapper employeeRequestWrapper){
        return employeeService.findByName(employeeRequestWrapper.getEmployeeQueryDTO(), employeeRequestWrapper.getFindAllEmployeesDTO());

    }

}










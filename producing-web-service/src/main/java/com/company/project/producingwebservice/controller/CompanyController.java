package com.company.controller;

import com.company.dto.*;
import com.company.entity.Company;
import com.company.entity.Department;
import com.company.service.CompanyService;
import com.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public CompanyDTO createCompany(@RequestBody CreateCompanyDTO createCompanyDTO){
        return companyService.createCompany(createCompanyDTO);
    }

    @GetMapping
    public List<Company> findAllCompany(@RequestBody FindAllCompaniesDTO findAllCompaniesDTO){
        return companyService.findAllCompany(findAllCompaniesDTO);
    }

    @GetMapping("/{companyId}")
    public Company findOne(@PathVariable("companyId") Long id){
        return companyService.findOne(id);
    }


    @PutMapping
    public CompanyDTO updateCompany( @RequestBody CreateCompanyDTO createCompanyDTO){
        return companyService.updateCompany(createCompanyDTO);
    }

    @DeleteMapping("/{companyId}")
    public CompanyDTO deleteCompany(@PathVariable("companyId") Long id){
        return companyService.deleteCompany(id);
    }

    @GetMapping("/{companyId}/departments")
    public List<DepartmentDTO> findDepartments(@PathVariable("companyId") Long id) {
        return companyService.findDepartments(id).stream().map(department -> departmentService.departmentToDTO(department)).collect(Collectors.toList());
    }

}



package com.company.project.producingwebservice.service;


import com.company.project.producingwebservice.dto.CompanyDTO;
import com.company.project.producingwebservice.dto.CreateCompanyDTO;
import com.company.project.producingwebservice.dto.FindAllCompaniesDTO;
import com.company.project.producingwebservice.entity.Company;
import com.company.project.producingwebservice.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompanyService {

    public List<Company> findAllCompany(FindAllCompaniesDTO findAllCompaniesDTO);

    public Company findOne(Long id);

    public CompanyDTO createCompany(CreateCompanyDTO createCompanyDTO);

    public CompanyDTO deleteCompany(Long id);

    public CompanyDTO updateCompany(CreateCompanyDTO createCompanyDTO);

    public Department addDepartment(Department dept, Long companyId);

    public Company deleteDepartment(Department dept, Company comp);

    public List<Department> findDepartments(Long id);
}

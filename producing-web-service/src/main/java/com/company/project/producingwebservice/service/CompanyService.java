package com.company.service;

import com.company.dto.CompanyDTO;
import com.company.dto.CreateCompanyDTO;
import com.company.dto.FindAllCompaniesDTO;
import com.company.entity.Company;
import com.company.entity.Department;

import java.util.List;
import java.util.Optional;

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

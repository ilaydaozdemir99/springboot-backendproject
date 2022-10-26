package com.company.service.Impl;

import com.company.dto.CompanyDTO;
import com.company.dto.CreateCompanyDTO;
import com.company.dto.FindAllCompaniesDTO;
import com.company.entity.Address;
import com.company.entity.Company;
import com.company.entity.Department;
import com.company.exception.IsEmptyException;
import com.company.repository.CompanyRepository;
import com.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAllCompany(FindAllCompaniesDTO findAllCompaniesDTO) {

        Integer page = findAllCompaniesDTO.getPage();
        Integer pageSize = findAllCompaniesDTO.getPageSize();
        String orderBy = findAllCompaniesDTO.getOrderBy();
        Pageable pageable;
        if(findAllCompaniesDTO.isDesc) {
            pageable = PageRequest.of(page, pageSize, Sort.by(orderBy).descending());
        }
        else {
            pageable = PageRequest.of(page, pageSize, Sort.by(orderBy).ascending());
        }
        return companyRepository.findAll(pageable).stream().collect(Collectors.toList());


    }

    @Override
    public Company findOne(Long id) {
        Optional<Company> comp = companyRepository.findById(id);
        if (comp.isEmpty()){
            throw new IsEmptyException("Empty id !!");
        }
        return comp.get();
    }

    @Override
    public CompanyDTO createCompany(CreateCompanyDTO createCompanyDTO) {
        Company comp = new Company();
        comp.setName(createCompanyDTO.getName());
        comp.setDescription(createCompanyDTO.getDescription());

        Address compAddress = new Address();
        compAddress.setCity(createCompanyDTO.getAddress().getCity());
        compAddress.setAddressNo(createCompanyDTO.getAddress().getAddressNo());
        compAddress.setProvince(createCompanyDTO.getAddress().getProvince());
        compAddress.setStreet(createCompanyDTO.getAddress().getStreet());
        comp.setAddress(compAddress);

        comp.setFounder(createCompanyDTO.getFounder());
        comp.setFoundationYear(createCompanyDTO.getFoundationYear());
        companyRepository.save(comp);
        return companyToDTO(comp);
    }

    @Override
    public CompanyDTO deleteCompany(Long id) {
        Optional<Company> comp = companyRepository.findById(id);
        if (comp.isEmpty()){
            throw new IsEmptyException("Empty id !!");
        }
        companyRepository.deleteById(id);
        return this.companyToDTO(comp.get());
    }

    @Override
    public CompanyDTO updateCompany( CreateCompanyDTO createCompanyDTO) {
        Optional<Company> compOpt = companyRepository.findById(createCompanyDTO.getId());
        Company comp = compOpt.get();
        comp.setName(createCompanyDTO.getName());
        comp.setDescription(createCompanyDTO.getDescription());
        comp.setFounder(createCompanyDTO.getFounder());
        comp.setFoundationYear(createCompanyDTO.getFoundationYear());
        comp.setAddress(createCompanyDTO.getAddress());
        companyRepository.save(comp);
        return companyToDTO(comp);
    }

    @Override
    public Department addDepartment(Department dept, Long companyId) {
        Optional<Company> comp = companyRepository.findById(companyId);
        comp.get().getDepartments().add(dept);
        return dept;
    }

    @Override
    public Company deleteDepartment(Department dept, Company comp) {
        comp.getDepartments().remove(dept);
        return comp;
    }

    @Override
    public List<Department> findDepartments(Long id) {
        Optional<Company> comp = companyRepository.findById(id);
        return comp.get().getDepartments();
    }

    public CompanyDTO companyToDTO(Company comp){
        CompanyDTO compDTO = new CompanyDTO();
        compDTO.setName(comp.getName());
        compDTO.setDescription(comp.getDescription());
        compDTO.setAddress(comp.getAddress());
        compDTO.setFounder(comp.getFounder());
        compDTO.setFoundationYear(comp.getFoundationYear());
        compDTO.setId(comp.getId());
        return compDTO;
    }
}

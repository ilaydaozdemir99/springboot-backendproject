package com.company.project.producingwebservice.service.Impl;

import com.company.project.producingwebservice.dto.CompanyDTO;
import com.company.project.producingwebservice.dto.CreateCompanyDTO;
import com.company.project.producingwebservice.dto.FindAllCompaniesDTO;
import com.company.project.producingwebservice.entity.Address;
import com.company.project.producingwebservice.entity.Company;
import com.company.project.producingwebservice.entity.Department;
import com.company.project.producingwebservice.enums.EmployeeColumnName;
import com.company.project.producingwebservice.exception.IsEmptyException;
import com.company.project.producingwebservice.exception.ItemNotFoundException;
import com.company.project.producingwebservice.repository.CompanyRepository;
import com.company.project.producingwebservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Company> findAllCompany(FindAllCompaniesDTO findAllCompaniesDTO) {
        try {
            Integer page = findAllCompaniesDTO.getPage();
            Integer pageSize = findAllCompaniesDTO.getPageSize();
            String orderBy = findAllCompaniesDTO.getOrderBy();

            String value = EmployeeColumnName.findValue(orderBy);
            Pageable pageable;
            if (findAllCompaniesDTO.isDesc) {
                pageable = PageRequest.of(page, pageSize, Sort.by(value).descending());
            } else {
                pageable = PageRequest.of(page, pageSize, Sort.by(value).ascending());
            }
            return companyRepository.findAll(pageable).stream().collect(Collectors.toList());
        }
        catch(Exception e){
            throw e;
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Company findOne(Long id) {
        try {
            Optional<Company> comp = companyRepository.findById(id);
            if (comp.isEmpty()) {
                throw new ItemNotFoundException("Item not found !!");
            }
            return comp.get();
        }
        catch(ItemNotFoundException e) {
            throw e;
        }
        catch(Exception e){
            throw e;
        }

    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CompanyDTO createCompany(CreateCompanyDTO createCompanyDTO) {
        try {
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
        catch(Exception e){
            throw e;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CompanyDTO deleteCompany(Long id) {
        try {
            Optional<Company> comp = companyRepository.findById(id);
            if (comp.isEmpty()) {
                throw new ItemNotFoundException("Item not found!!");
            }
            companyRepository.deleteById(id);
            return this.companyToDTO(comp.get());
        }
        catch(ItemNotFoundException e) {
             throw e;
        }
        catch(Exception e){
            throw e;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CompanyDTO updateCompany( CreateCompanyDTO createCompanyDTO) {
        try {
            Optional<Company> compOpt = companyRepository.findById(createCompanyDTO.getId());
            if(compOpt.isEmpty()){
                throw new ItemNotFoundException("Item not found!!");
            }
            Company comp = compOpt.get();
            comp.setName(createCompanyDTO.getName());
            comp.setDescription(createCompanyDTO.getDescription());
            comp.setFounder(createCompanyDTO.getFounder());
            comp.setFoundationYear(createCompanyDTO.getFoundationYear());
            comp.setAddress(createCompanyDTO.getAddress());
            companyRepository.save(comp);
            return companyToDTO(comp);
        }
        catch(ItemNotFoundException e) {
            throw e;

        }
        catch(Exception e){
            throw e;
        }

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

    @Transactional(propagation = Propagation.REQUIRED)
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

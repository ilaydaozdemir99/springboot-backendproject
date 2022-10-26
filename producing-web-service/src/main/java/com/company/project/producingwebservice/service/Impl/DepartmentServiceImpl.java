package com.company.project.producingwebservice.service.Impl;

import com.company.project.producingwebservice.dto.CreateDepartmentDTO;
import com.company.project.producingwebservice.dto.DepartmentDTO;
import com.company.project.producingwebservice.dto.FindAllDepartmentsDTO;
import com.company.project.producingwebservice.entity.Department;
import com.company.project.producingwebservice.entity.Employee;
import com.company.project.producingwebservice.enums.EmployeeColumnName;
import com.company.project.producingwebservice.exception.IsEmptyException;
import com.company.project.producingwebservice.exception.ItemNotFoundException;
import com.company.project.producingwebservice.repository.DepartmentRepository;
import com.company.project.producingwebservice.service.CompanyService;
import com.company.project.producingwebservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyService companyService;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Department> findAllDepartment(FindAllDepartmentsDTO findAllDepartmentsDTO) {
        try {
            Integer page = findAllDepartmentsDTO.getPage();
            Integer pageSize = findAllDepartmentsDTO.getPageSize();
            String orderBy = findAllDepartmentsDTO.getOrderBy();

            String value = EmployeeColumnName.findValue(orderBy);
            Pageable pageable;
            if (findAllDepartmentsDTO.isDesc) {
                pageable = PageRequest.of(page, pageSize, Sort.by(value).descending());
            } else {
                pageable = PageRequest.of(page, pageSize, Sort.by(value).ascending());
            }
            return departmentRepository.findAll(pageable).stream().collect(Collectors.toList());
        }
        catch(Exception e){
            throw e;
        }

    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Department findOne(Long id) {
        try {
            Optional<Department> dept = departmentRepository.findById(id);
            if (dept.isEmpty()) {
                throw new ItemNotFoundException("Item not found !!");
            }
            return dept.get();
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
    public DepartmentDTO createDepartment(CreateDepartmentDTO createDepartmentDTO) {
        try {
            Department dept = new Department();
            dept.setName(createDepartmentDTO.getName());
            dept.setDescription(createDepartmentDTO.getDescription());
            dept.setCompany(companyService.findOne(createDepartmentDTO.getCompanyId()));
            departmentRepository.save(dept);
            return departmentToDTO(dept);
        }
        catch(Exception e){
            throw e;
        }

    }

    public DepartmentDTO departmentToDTO(Department dept) {
        DepartmentDTO deptDTO = new DepartmentDTO();
        deptDTO.setDescription(dept.getDescription());
        deptDTO.setName(dept.getName());
        deptDTO.setId(dept.getId());
        deptDTO.setCompanyId(dept.getCompany().getId());
        return deptDTO;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public DepartmentDTO deleteDepartment(Long id) {
        try {
            Optional<Department> dept = departmentRepository.findById(id);
            if (dept.isEmpty()) {
                throw new ItemNotFoundException("Item not found !!");
            }
            departmentRepository.deleteById(id);
            return this.departmentToDTO(dept.get());
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
    public DepartmentDTO updateDepartment(CreateDepartmentDTO createDepartmentDTO) {
        try {
            Optional<Department> deptOptional = departmentRepository.findById(createDepartmentDTO.getId());
            if(deptOptional.isEmpty()){
                throw new ItemNotFoundException("Item not found !!");
            }
            Department dept = deptOptional.get();
            dept.setName(createDepartmentDTO.getName());
            dept.setDescription(createDepartmentDTO.getDescription());
            dept.setCompany(companyService.findOne(createDepartmentDTO.getCompanyId()));
            departmentRepository.save(dept);
            return departmentToDTO(dept);
        }
        catch(ItemNotFoundException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }

    }

    @Override
    public Department addEmployee(Employee emp, Long departmentId) {
        Optional<Department> dept = departmentRepository.findById(departmentId);
        dept.get().getEmployees().add(emp);
        return dept.get();
    }

    @Override
    public Department deleteEmployee(Employee emp, Department dept) {
        dept.getEmployees().remove(emp);
        return dept;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Employee> findEmployees(Long id) {
        Department dept = findOne(id);
        return dept.getEmployees();
    }
}

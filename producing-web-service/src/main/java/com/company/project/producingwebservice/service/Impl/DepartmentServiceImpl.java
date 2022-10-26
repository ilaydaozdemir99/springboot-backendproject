package com.company.service.Impl;

import com.company.dto.CreateDepartmentDTO;
import com.company.dto.DepartmentDTO;
import com.company.dto.FindAllDepartmentsDTO;
import com.company.entity.Company;
import com.company.entity.Department;
import com.company.entity.Employee;
import com.company.exception.IsEmptyException;
import com.company.repository.DepartmentRepository;
import com.company.service.CompanyService;
import com.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyService companyService;


    @Override
    public List<Department> findAllDepartment(FindAllDepartmentsDTO findAllDepartmentsDTO) {

        Integer page = findAllDepartmentsDTO.getPage();
        Integer pageSize = findAllDepartmentsDTO.getPageSize();
        String orderBy = findAllDepartmentsDTO.getOrderBy();
        Pageable pageable;
        if(findAllDepartmentsDTO.isDesc) {
            pageable = PageRequest.of(page, pageSize, Sort.by(orderBy).descending());
        }
        else {
            pageable = PageRequest.of(page, pageSize, Sort.by(orderBy).ascending());
        }
        return departmentRepository.findAll(pageable).stream().collect(Collectors.toList());




    }

    @Override
    public Department findOne(Long id) {
        Optional<Department> dept = departmentRepository.findById(id);
        if (dept.isEmpty()){
            throw new IsEmptyException("Empty id !!");
        }
        return dept.get();
    }

    @Override
    public DepartmentDTO createDepartment(CreateDepartmentDTO createDepartmentDTO) {
        Department dept = new Department();
        dept.setName(createDepartmentDTO.getName());
        dept.setDescription(createDepartmentDTO.getDescription());
        dept.setCompany(companyService.findOne(createDepartmentDTO.getCompanyId()));
        departmentRepository.save(dept);
        return departmentToDTO(dept);

    }

    public DepartmentDTO departmentToDTO(Department dept) {
        DepartmentDTO deptDTO = new DepartmentDTO();
        deptDTO.setDescription(dept.getDescription());
        deptDTO.setName(dept.getName());
        deptDTO.setId(dept.getId());
        deptDTO.setCompanyId(dept.getCompany().getId());
        return deptDTO;
    }

    @Override
    public DepartmentDTO deleteDepartment(Long id) {
        Optional<Department> dept = departmentRepository.findById(id);
        if (dept.isEmpty()){
            throw new IsEmptyException("Empty id !!");
        }
        departmentRepository.deleteById(id);
        return this.departmentToDTO(dept.get());
    }

    @Override
    public DepartmentDTO updateDepartment(CreateDepartmentDTO createDepartmentDTO) {
        Optional<Department> deptOptional = departmentRepository.findById(createDepartmentDTO.getId());
        Department dept = deptOptional.get();
        dept.setName(createDepartmentDTO.getName());
        dept.setDescription(createDepartmentDTO.getDescription());
        dept.setCompany(companyService.findOne(createDepartmentDTO.getCompanyId()));
        departmentRepository.save(dept);
        return departmentToDTO(dept);
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

    @Override
    public List<Employee> findEmployees(Long id) {
        Department dept = findOne(id);
        return dept.getEmployees();
    }
}

package com.company.project.producingwebservice.service.Impl;

import com.company.project.producingwebservice.dto.*;
import com.company.project.producingwebservice.entity.Address;
import com.company.project.producingwebservice.entity.Employee;
import com.company.project.producingwebservice.entity.Title;
import com.company.project.producingwebservice.enums.EmployeeColumnName;
import com.company.project.producingwebservice.exception.ItemNotFoundException;
import com.company.project.producingwebservice.repository.EmployeeRepository;
import com.company.project.producingwebservice.service.DepartmentService;
import com.company.project.producingwebservice.service.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Employee> findAllEmployee(FindAllEmployeesDTO findAllEmployeesDTO) {
        try {
            Integer page = findAllEmployeesDTO.getPage();
            Integer pageSize = findAllEmployeesDTO.getPageSize();
            String orderBy = findAllEmployeesDTO.getOrderBy();

            String value = EmployeeColumnName.findValue(orderBy);

            Pageable pageable;
            if (findAllEmployeesDTO.isDesc) {
                pageable = PageRequest.of(page, pageSize, Sort.by(value).descending());
            } else {
                pageable = PageRequest.of(page, pageSize, Sort.by(value).ascending());
            }
            return employeeRepository.findAll(pageable).stream().collect(Collectors.toList());
        }
        catch(Exception e){
            throw e;
        }

    }




    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Employee findOne(Long id) {
           try {
               Optional<Employee> emp = employeeRepository.findById(id);
               if (emp.isEmpty()) {
                   throw new ItemNotFoundException("Item not found !!");
               }
               return emp.get();
           }
           catch(ItemNotFoundException e){
               throw e;
           }
           catch(Exception e){
               throw e;
           }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public EmployeeDTO createEmployee(CreateEmployee createEmployeeDTO) {
        try {
            Employee emp = new Employee();
            emp.setName(createEmployeeDTO.getName());
            emp.setDescription(createEmployeeDTO.getDescription());
            emp.setStartDate(createEmployeeDTO.getStartDate());
            Title title = new Title();
            title.setName(createEmployeeDTO.getTitle().getName());
            title.setDescription(createEmployeeDTO.getTitle().getDescription());
            emp.setTitle(title);

            Address address = new Address();
            address.setProvince(createEmployeeDTO.getAddress().getProvince());
            address.setAddressNo(createEmployeeDTO.getAddress().getAddressNo());
            address.setCity(createEmployeeDTO.getAddress().getCity());
            address.setStreet(createEmployeeDTO.getAddress().getStreet());

            emp.setAddress(address);

            emp.setDepartment(departmentService.findOne(createEmployeeDTO.getDepartmentId()));

            employeeRepository.save(emp);
            return employeeToDTO(emp);
        }
        catch(Exception e){
            throw e;
        }

    }

    public EmployeeDTO employeeToDTO(Employee emp) {
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setDescription(emp.getDescription());
        empDTO.setName(emp.getName());
        empDTO.setId(emp.getId());
        empDTO.setDepartmentId(emp.getDepartment().getId());
        empDTO.setStartDate(emp.getStartDate());

        empDTO.setAddress(emp.getAddress());
        empDTO.setTitle(emp.getTitle());

        return empDTO;
    }

    @Override
    public List<EmployeeQueryDTO> findAllByQuery() {
        return employeeRepository.findAllByQuery();
    }

    @Override
    public List<EmployeeQueryDTO> findByName(EmployeeQueryDTO employeeQueryDTO, FindAllEmployeesDTO findAllEmployeesDTO) {
        try {
            Integer page = findAllEmployeesDTO.getPage();
            Integer pageSize = findAllEmployeesDTO.getPageSize();
            String orderBy = findAllEmployeesDTO.getOrderBy();

            String value = EmployeeColumnName.findValue(orderBy);

            Pageable pageable;
            if (findAllEmployeesDTO.isDesc) {
                pageable = PageRequest.of(page, pageSize, Sort.by(value).descending());
            } else {
                pageable = PageRequest.of(page, pageSize, Sort.by(value).ascending());
            }
            return employeeRepository.findByName(employeeQueryDTO,pageable).stream().collect(Collectors.toList());
        }
        catch(Exception e){
            throw e;
        }

    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public EmployeeDTO deleteEmployee(Long id) {
        try {
            Optional<Employee> emp = employeeRepository.findById(id);
            if (emp.isEmpty()) {
                throw new ItemNotFoundException("Item not found!!");
            }
            employeeRepository.deleteById(id);
            return this.employeeToDTO(emp.get());
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
    public EmployeeDTO updateEmployee(CreateEmployeeDTO createEmployeeDTO) {
        try {
            Optional<Employee> empOptional = employeeRepository.findById(createEmployeeDTO.getId());
            if(empOptional.isEmpty()){
                throw new ItemNotFoundException("Item not found !!");
            }
            Employee emp = empOptional.get();
            emp.setDepartment(departmentService.findOne(createEmployeeDTO.getDepartmentId()));
            emp.setName(createEmployeeDTO.getName());
            emp.setDescription(createEmployeeDTO.getDescription());
            emp.setStartDate(createEmployeeDTO.getStartDate());
            emp.setAddress(createEmployeeDTO.getAddress());
            emp.setTitle(createEmployeeDTO.getTitle());
            employeeRepository.save(emp);
            return employeeToDTO(emp);
        }
        catch(ItemNotFoundException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }
    }
}

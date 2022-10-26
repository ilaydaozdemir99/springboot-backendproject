package com.company.service.Impl;

import com.company.dto.CreateEmployee;
import com.company.dto.CreateEmployeeDTO;
import com.company.dto.EmployeeDTO;
import com.company.dto.FindAllEmployeesDTO;
import com.company.entity.Address;
import com.company.entity.Department;
import com.company.entity.Employee;
import com.company.entity.Title;
import com.company.exception.IsEmptyException;
import com.company.repository.EmployeeRepository;
import com.company.service.DepartmentService;
import com.company.service.EmployeeService;
import com.company.utils.AddressIdGenerator;
import com.company.utils.TitleIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;


    @Override
    public List<Employee> findAllEmployee(FindAllEmployeesDTO findAllEmployeesDTO) {

        Integer page = findAllEmployeesDTO.getPage();
        Integer pageSize = findAllEmployeesDTO.getPageSize();
        String orderBy = findAllEmployeesDTO.getOrderBy();
        Pageable pageable;
        if(findAllEmployeesDTO.isDesc) {
            pageable = PageRequest.of(page, pageSize, Sort.by(orderBy).descending());
        }
        else {
           pageable = PageRequest.of(page, pageSize, Sort.by(orderBy).ascending());
        }
        return employeeRepository.findAll(pageable).stream().collect(Collectors.toList());


    }



    @Override
    public Employee findOne(Long id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isEmpty()){
            throw new IsEmptyException("Empty id !!");
        }
        return emp.get();

    }

    @Override
    public EmployeeDTO createEmployee(CreateEmployee createEmployeeDTO) {
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
    public EmployeeDTO deleteEmployee(Long id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isEmpty()){
            throw new IsEmptyException("Empty id !!");
        }
        employeeRepository.deleteById(id);
        return this.employeeToDTO(emp.get());
    }

    @Override
    public EmployeeDTO updateEmployee(CreateEmployeeDTO createEmployeeDTO) {
        Optional<Employee> empOptional = employeeRepository.findById(createEmployeeDTO.getId());
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
}

package com.company.project.producingwebservice.wrapper;

import com.company.project.producingwebservice.dto.EmployeeQueryDTO;
import com.company.project.producingwebservice.dto.FindAllEmployeesDTO;

public class EmployeeRequestWrapper {

    public EmployeeQueryDTO employeeQueryDTO;

    public FindAllEmployeesDTO findAllEmployeesDTO;

    public EmployeeQueryDTO getEmployeeQueryDTO() {
        return employeeQueryDTO;
    }

    public void setEmployeeQueryDTO(EmployeeQueryDTO employeeQueryDTO) {
        this.employeeQueryDTO = employeeQueryDTO;
    }

    public FindAllEmployeesDTO getFindAllEmployeesDTO() {
        return findAllEmployeesDTO;
    }

    public void setFindAllEmployeesDTO(FindAllEmployeesDTO findAllEmployeesDTO) {
        this.findAllEmployeesDTO = findAllEmployeesDTO;
    }
}

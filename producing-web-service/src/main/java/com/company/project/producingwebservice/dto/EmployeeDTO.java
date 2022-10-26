package com.company.dto;

import com.company.entity.Address;
import com.company.entity.Department;
import com.company.entity.Title;
import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;

    private Long departmentId;

    private String name;

    private String startDate;

    private String description;

    private Address address;

    private Title title;
}



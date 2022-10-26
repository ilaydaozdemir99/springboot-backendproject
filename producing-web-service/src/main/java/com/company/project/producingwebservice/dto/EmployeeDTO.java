package com.company.project.producingwebservice.dto;


import com.company.project.producingwebservice.entity.Address;
import com.company.project.producingwebservice.entity.Title;
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



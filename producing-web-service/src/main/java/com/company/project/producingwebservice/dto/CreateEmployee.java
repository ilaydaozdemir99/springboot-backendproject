package com.company.dto;

import com.company.entity.Address;
import com.company.entity.Title;
import lombok.Data;

@Data
public class CreateEmployee {

    private Long departmentId;

    private String name;

    private String startDate;

    private String description;

    private Title title;

    private Address address;



}


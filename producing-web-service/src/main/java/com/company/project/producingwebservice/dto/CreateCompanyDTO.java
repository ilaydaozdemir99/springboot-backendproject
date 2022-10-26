package com.company.project.producingwebservice.dto;


import com.company.project.producingwebservice.entity.Address;
import lombok.Data;

@Data
public class CreateCompanyDTO {

    private Long id;

    private String name;

    private String founder;

    private String foundationYear;

    private String description;

    private Address address;
}
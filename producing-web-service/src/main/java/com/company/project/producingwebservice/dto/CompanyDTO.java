package com.company.project.producingwebservice.dto;


import com.company.project.producingwebservice.entity.Address;

public class CompanyDTO {
    private Long id;

    private String name;

    private String founder;

    private String foundationYear;

    private String description;

    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(String foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CompanyDTO(Long id, String name, String founder, String foundationYear, String description, Address address) {
        this.id = id;
        this.name = name;
        this.founder = founder;
        this.foundationYear = foundationYear;
        this.description = description;
        this.address = address;
    }

    public CompanyDTO() {
    }
}

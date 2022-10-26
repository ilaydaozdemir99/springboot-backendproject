package com.company.dto;

import com.company.entity.Employee;

import java.util.List;

public class DepartmentDTO {

    private Long id;
    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    private String name;

    private String description;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DepartmentDTO() {
    }

    public DepartmentDTO( String name, String description) {

        this.name = name;
        this.description = description;
    }
}


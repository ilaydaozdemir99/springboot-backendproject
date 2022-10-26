package com.company.dto;

public class CreateDepartmentDTO {

    private Long id;
    private Long companyId;

    private String name;

    private String description;

    public CreateDepartmentDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateDepartmentDTO(Long id, Long companyId, String name, String description) {
        this.id = id;
        this.companyId = companyId;
        this.name = name;
        this.description = description;
    }





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

    public CreateDepartmentDTO(Long companyId) {
        this.companyId = companyId;
    }

    public CreateDepartmentDTO(Long companyId, String name, String description) {
        this.companyId = companyId;
        this.name = name;
        this.description = description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}


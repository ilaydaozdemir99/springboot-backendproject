package com.company.project.producingwebservice.dto;


import com.company.project.producingwebservice.entity.Address;
import com.company.project.producingwebservice.entity.Title;
import lombok.Data;

@Data
public class CreateEmployeeDTO {

    private Long departmentId;

    private Long id;

    private String name;

    private String startDate;

    private String description;

    private Address address;



    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    private Title title;


}


package com.company.project.producingwebservice.dto;

import com.company.project.producingwebservice.entity.Address;
import com.company.project.producingwebservice.entity.Title;

public class EmployeeQueryDTO {

    public Long id;

    public String name;

    public String startDate;

    public String dept;

    public String comp;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
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

    public EmployeeQueryDTO(long id, String name, String startDate, String dept , String comp){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.dept = dept;
        this.comp = comp;
    }

}

package com.company.project.producingwebservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Company company;

    @OneToMany(mappedBy = "department", targetEntity = Employee.class)
    @JsonManagedReference
    private List<Employee> employees;
}

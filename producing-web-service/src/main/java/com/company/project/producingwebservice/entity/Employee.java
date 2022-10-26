package com.company.project.producingwebservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String startDate;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    @JsonBackReference
    private Title title ;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Department department;
}

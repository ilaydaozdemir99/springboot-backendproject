package com.company.project.producingwebservice.enums;

import com.company.project.producingwebservice.exception.IsEmptyException;

import java.util.Arrays;
import java.util.Optional;

public enum DepartmentColumnName {
    id("id"),
    name("name"),
    description("description");


    String value;

    DepartmentColumnName(String value) {
        this.value = value;
    }

    public static String findValue(String text){
        Optional<DepartmentColumnName> departmentColumnName = Arrays.stream(values()).filter(value -> value.toString().equalsIgnoreCase(text)).findFirst();
        if (departmentColumnName.isPresent()){
            return departmentColumnName.get().toString();
        }

        throw new IsEmptyException("INCORRECT VALUE !");
    }
}

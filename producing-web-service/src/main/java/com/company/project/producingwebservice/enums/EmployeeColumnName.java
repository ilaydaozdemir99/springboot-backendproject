package com.company.project.producingwebservice.enums;

import com.company.project.producingwebservice.exception.IsEmptyException;

import java.util.Arrays;
import java.util.Optional;

public enum EmployeeColumnName {

    id("id"),
    name("name"),
    description("description");

    String value;

    EmployeeColumnName(String value){
        this.value = value;
    }

    public static String findValue(String text){
        Optional<EmployeeColumnName> employeeColumnName = Arrays.stream(values()).filter(value -> value.toString().equalsIgnoreCase(text)).findFirst();
       if (employeeColumnName.isPresent()){
           return employeeColumnName.get().toString();
       }

       throw new IsEmptyException("INCORRECT VALUE !");
    }

}

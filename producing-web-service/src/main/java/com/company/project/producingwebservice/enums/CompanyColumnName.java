package com.company.project.producingwebservice.enums;

import com.company.project.producingwebservice.exception.IsEmptyException;

import java.util.Arrays;
import java.util.Optional;

public enum CompanyColumnName {

      id("id"),
      name("name"),
      foundationYear("foundationYear");

      String value;


      CompanyColumnName(String value){
          this.value= value;
      }

    public static String findValue(String text){
        Optional<CompanyColumnName> companyColumnName = Arrays.stream(values()).filter(value -> value.toString().equalsIgnoreCase(text)).findFirst();
        if (companyColumnName.isPresent()){
            return companyColumnName.get().toString();
        }

        throw new IsEmptyException("INCORRECT VALUE !");
    }


}

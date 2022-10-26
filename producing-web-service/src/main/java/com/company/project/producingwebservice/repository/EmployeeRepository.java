package com.company.project.producingwebservice.repository;


import com.company.project.producingwebservice.dto.EmployeeQueryDTO;
import com.company.project.producingwebservice.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

     @Query("select new com.company.project.producingwebservice.dto.EmployeeQueryDTO(e.id, e.name, e.startDate, d.name, c.name) from Employee e inner join Department d on e.department.id= d.id inner join Company c on d.company.id = c.id ")
     public List<EmployeeQueryDTO> findAllByQuery();

    //jpa projection

     @Query("select new com.company.project.producingwebservice.dto.EmployeeQueryDTO(e.id, e.name, e.startDate, d.name, c.name) from Employee e inner join Department d on e.department.id= d.id inner join Company c on d.company.id = c.id where e.name =:#{#employeeQueryDTO.name} and d.name =:#{#employeeQueryDTO.dept} and c.name =:#{#employeeQueryDTO.comp}")
     public List<EmployeeQueryDTO> findByName(@Param("employeeQueryDTO") EmployeeQueryDTO employeeQueryDTO, Pageable pageable);


}

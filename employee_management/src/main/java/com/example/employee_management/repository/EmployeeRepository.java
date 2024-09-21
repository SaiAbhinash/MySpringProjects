package com.example.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.employee_management.entity.EmployeeEntity;

import java.util.Optional;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByEmail(String email);
    List<EmployeeEntity> findByProjectNameAndPhoneNumber(String projectName, String phoneNumber);
    //List<EmployeeEntity> findTop5ByProjectNameAndSalaryGreaterThan(String projectName, int salary);
    @Query(nativeQuery = true, value = "SELECT e FROM Employee e WHERE e.projectName = :projectName AND e.salary > :salary ORDER BY e.id ASC")
    List<EmployeeEntity> findTop5ByProjectNameAndSalaryGreaterThan(
            @Param("projectName") String projectName, 
            @Param("salary") int salary);
}

   


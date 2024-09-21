package com.example.employee_management.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String projectName;
    private String address;
    private Integer salary;
}
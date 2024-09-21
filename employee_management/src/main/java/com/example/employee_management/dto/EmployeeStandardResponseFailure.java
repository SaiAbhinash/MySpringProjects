package com.example.employee_management.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeStandardResponseFailure {
	private Integer statusCode;
	private boolean status;
	private String responseDescription;

}

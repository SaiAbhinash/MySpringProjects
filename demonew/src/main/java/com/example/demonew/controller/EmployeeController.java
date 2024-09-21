package com.example.demonew.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demonew.dto.models.EmployeeModel;
import com.example.demonew.dto.models.EmployeeResponse;
import com.example.demonew.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	//EmployeeModel employee;
	private EmployeeService employeeservice;
		@PostMapping("/add")
		public ResponseEntity<EmployeeResponse> addEmployeeInfo(@RequestBody EmployeeModel employee){
			EmployeeResponse response=employeeservice.addEmployeeService(employee);
			if(response.getStatus() == true) {
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
		
		@GetMapping("/find-employee/{e_id}")
		public ResponseEntity<?> getemployeeData(@PathVariable("e_id") Integer e_id) {
			EmployeeResponse employeeDataResponse = employeeservice.getemployeedata(e_id);
			if (employeeDataResponse.getStatus() == true) {
				return new ResponseEntity<>(employeeDataResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(employeeDataResponse, HttpStatus.NOT_FOUND);
			}
		
	}
}


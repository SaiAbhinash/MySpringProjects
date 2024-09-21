package com.example.employee_management.controller;

import com.example.employee_management.dto.EmployeeDTO;

import com.example.employee_management.entity.EmployeeEntity;
import com.example.employee_management.service.EmployeeService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/datasaved")
    @Tag(name = "save", description = "Employee Data Saved to database")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
    	try {
    		EmployeeEntity savedEmployee = employeeService.saveEmployee(employeeDTO);
            logger.info("Employee created successfully: {}", savedEmployee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        } catch (Exception e) {
            logger.error("Error creating employee", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<Optional<EmployeeEntity>> getEmployeeById(@PathVariable Long id) {
    	try {
            Optional<EmployeeEntity> employee = employeeService.getEmployeeById(id);
            if (employee.isPresent()) {
                logger.info("Employee found with ID: {}", id);
                return ResponseEntity.ok(employee);
            } else {
                logger.warn("Employee not found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Error fetching employee with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<Optional<EmployeeEntity>> getEmployeeByEmail(@RequestParam String email) {
    	try {
            Optional<EmployeeEntity> employee = employeeService.getEmployeeByEmail(email);
            if (employee.isPresent()) {
                logger.info("Employee found with email: {}", email);
                return ResponseEntity.ok(employee);
            } else {
                logger.warn("Employee not found with email: {}", email);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Error fetching employee with email: {}", email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//    example link : http://localhost:8080/api/employees?email=examplemail
    
    @GetMapping("/search")   
    public ResponseEntity<List<EmployeeEntity>> getEmployeesByProjectAndPhone(
            @RequestParam String projectName,
            @RequestParam String phoneNumber) {
    	try {
            List<EmployeeEntity> employees = employeeService.getEmployeesByProjectAndPhone(projectName, phoneNumber);
            logger.info("Employees found with projectName: {} and phoneNumber: {}", projectName, phoneNumber);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            logger.error("Error fetching employees with projectName: {} and phoneNumber: {}", projectName, phoneNumber, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }      
//    example link : http://localhost:8080/api/employees/search?projectName=exampleProjectname&phoneNumber=examplephnnum

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
    	try {
            List<EmployeeEntity> employees = employeeService.getAllEmployees();
            logger.info("Fetched all employees, count: {}", employees.size());
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            logger.error("Error fetching all employees", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<EmployeeEntity>> getTop5EmployeesByProjectAndSalary(
            @RequestParam String projectName,
            @RequestParam int salary) {
        try {
            List<EmployeeEntity> employees = employeeService.getTop5EmployeesByProjectAndSalary(projectName, salary);
            logger.info("Fetched top 5 employees for project: {} with salary greater than: {}", projectName, salary);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            logger.error("Error fetching employees with project: {} and salary: {}", projectName, salary, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//  example link : http://localhost:8080/api/employees/filter?projectName=BMO&salary=200000
    

    @PutMapping("update-data/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
    	try {
    		EmployeeEntity updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
            logger.info("Employee updated successfully: {}", updatedEmployee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            logger.warn("Employee not found for update with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error updating employee with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    	try {
            employeeService.deleteEmployee(id);
            logger.info("Employee deleted with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting employee with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

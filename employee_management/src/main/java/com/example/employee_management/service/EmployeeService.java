package com.example.employee_management.service;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.entity.EmployeeEntity;
import com.example.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
	 private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity saveEmployee(EmployeeDTO employeeDTO) {
    	EmployeeEntity employee = new EmployeeEntity();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setProjectName(employeeDTO.getProjectName());
        employee.setAddress(employeeDTO.getAddress());
        employee.setSalary(employeeDTO.getSalary());
        logger.debug("Saving employee: {}", employee);
        return employeeRepository.save(employee);
    }

    public Optional<EmployeeEntity> getEmployeeById(Long id) {
    	logger.debug("Fetching employee by ID: {}", id);
        return employeeRepository.findById(id);
    }

    public Optional<EmployeeEntity> getEmployeeByEmail(String email) {
    	logger.debug("Fetching employee by email: {}", email);
        return employeeRepository.findByEmail(email);
    }

    public List<EmployeeEntity> getAllEmployees() {
    	 logger.debug("Fetching all employees");
        return employeeRepository.findAll();
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeDTO employeeDTO) {
    	logger.debug("Updating employee with ID: {}", id);
    	EmployeeEntity employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        if (employeeDTO.getPhoneNumber() != null) {
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        }
        if (employeeDTO.getProjectName() != null) {
            employee.setProjectName(employeeDTO.getProjectName());
        }
        if(employeeDTO.getSalary()!=null) {
        	employee.setSalary(employeeDTO.getSalary());
        }
        if(employeeDTO.getAddress()!=null) {
        	employee.setAddress(employeeDTO.getAddress());
        }
        return employeeRepository.save(employee);
    }
    
    public List<EmployeeEntity> getEmployeesByProjectAndPhone(String projectName, String phoneNumber) {
    	logger.debug("Fetching employees by project: {} and phone: {}", projectName, phoneNumber);
        return employeeRepository.findByProjectNameAndPhoneNumber(projectName, phoneNumber);
    }
    
    public List<EmployeeEntity> getTop5EmployeesByProjectAndSalary(String projectName, int salary) {
        return employeeRepository.findTop5ByProjectNameAndSalaryGreaterThan(projectName, salary);
    }

    public void deleteEmployee(Long id) {
    	logger.debug("Deleting employee with ID: {}", id);
        employeeRepository.deleteById(id);
    }
}

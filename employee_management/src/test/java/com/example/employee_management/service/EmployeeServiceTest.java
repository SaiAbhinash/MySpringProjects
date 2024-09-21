package com.example.employee_management.service;

import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.entity.EmployeeEntity;
import com.example.employee_management.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveEmployee() {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setName("John Doe");
		employeeDTO.setEmail("john.doe@example.com");
		employeeDTO.setPhoneNumber("1234567890");
		employeeDTO.setProjectName("Project A");

		EmployeeEntity employee = new EmployeeEntity();
		employee.setId(1L);
		employee.setName(employeeDTO.getName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setProjectName(employeeDTO.getProjectName());

		when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employee);

		EmployeeEntity savedEmployee = employeeService.saveEmployee(employeeDTO);

		assertEquals("John Doe", savedEmployee.getName());
		verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
	}


	@Test
	void testGetTop5EmployeesByProjectAndSalary_Success() {
	    Logger logger = LoggerFactory.getLogger(this.getClass());

	    // Arrange
	    EmployeeEntity emp1 = new EmployeeEntity();
	    emp1.setId(1L);
	    emp1.setName("John");
	    emp1.setProjectName("BMO");
	    emp1.setSalary(250000);

	    EmployeeEntity emp2 = new EmployeeEntity();
	    emp2.setId(2L);
	    emp2.setName("Jane");
	    emp2.setProjectName("BMO");
	    emp2.setSalary(300000);

	    List<EmployeeEntity> employees = Arrays.asList(emp1, emp2);

	    // Log to check what we are mocking
	    logger.info("Mocking employees: {}", employees);

	    // Mock the repository to return the arranged list of employees
	    when(employeeRepository.findTop5ByProjectNameAndSalaryGreaterThan("BMO", 200000))
	            .thenReturn(employees);

	    // Act
	    List<EmployeeEntity> result = employeeService.getTop5EmployeesByProjectAndSalary("BMO", 200000);

	    // Log the result to debug
	    logger.info("Result from service: {}", result);

	    // Assert
	    assertEquals(2, result.size(), "The result size should be 2");
	    assertEquals("John", result.get(0).getName(), "First employee's name should be John");
	    assertEquals("Jane", result.get(1).getName(), "Second employee's name should be Jane");

	    // Verify that the repository was called exactly once
	    verify(employeeRepository, times(1)).findTop5ByProjectNameAndSalaryGreaterThan("BMO", 200000);
	}


	@Test
	void testGetTop5EmployeesByProjectAndSalary_NoResults() {
		// Arrange
		when(employeeRepository.findTop5ByProjectNameAndSalaryGreaterThan("BMO", 200000)).thenReturn(Arrays.asList());

		// Act
		List<EmployeeEntity> result = employeeService.getTop5EmployeesByProjectAndSalary("BMO", 200000);

		// Assert
		assertTrue(result.isEmpty());
		verify(employeeRepository, times(1)).findTop5ByProjectNameAndSalaryGreaterThan("BMO", 200000);
	}

	@Test
	void testGetTop5EmployeesByProjectAndSalary_Exception() {
		// Arrange
		when(employeeRepository.findTop5ByProjectNameAndSalaryGreaterThan("BMO", 200000))
				.thenThrow(new RuntimeException("Database error"));

		// Act & Assert
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			employeeService.getTop5EmployeesByProjectAndSalary("BMO", 200000);
		});
		assertEquals("Database error", exception.getMessage());
		verify(employeeRepository, times(1)).findTop5ByProjectNameAndSalaryGreaterThan("BMO", 200000);
	}
	
	@DataJpaTest
	public class EmployeeRepositoryTest {

	    @Autowired
	    private EmployeeRepository employeeRepository;

	    @Test
	    public void testFindTop5ByProjectNameAndSalaryGreaterThan() {
	        // Setup and save test data in the repository here

	        List<EmployeeEntity> result = employeeRepository.findTop5ByProjectNameAndSalaryGreaterThan("BMO", 200000);
	        assertNotNull(result);
	        assertFalse(result.isEmpty());
	    }
	}
}

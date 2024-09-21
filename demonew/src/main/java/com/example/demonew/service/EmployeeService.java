package com.example.demonew.service;
import java.util.ArrayList;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demonew.dto.models.EmployeeModel;
import com.example.demonew.dto.models.EmployeeResponse;
import com.example.demonew.dto.models.GetEmployeeResponse;


@Service
public class EmployeeService {
	
	List<EmployeeModel> employeeInfo = new ArrayList<>();
	public EmployeeResponse addEmployeeService(EmployeeModel employee) {
		EmployeeResponse response=new EmployeeResponse();
		
		if(employee !=null) {
			EmployeeModel employeeTwo = new EmployeeModel();
			employeeTwo.setE_name(employee.getE_name());
			employeeTwo.setE_id(employee.getE_id());
			employeeTwo.setE_salary(employee.getE_salary());
			employeeTwo.setE_projects(employee.getE_projects());
			
			employeeInfo.add(employeeTwo);
			response.setResponseDescription("Added Successfully");
			response.setStatus(true);
			response.setStatusCode(201);
			
		}
		else {
		response.setResponseDescription("Not Added Successfully");
		response.setStatus(false);
		response.setStatusCode(500);
	}
		return response;
		
	}

	public EmployeeResponse getemployeedata(Integer e_id) {
		List<String> projectsList=Arrays.asList("citi", "bmo", "fizser");
		employeeInfo.add(new EmployeeModel("Abhi", 10, 100000, projectsList));
		employeeInfo.add(new EmployeeModel("sai", 13, 200000, projectsList));
		employeeInfo.add(new EmployeeModel("chandu", 12, 220000, projectsList));
		employeeInfo.add(new EmployeeModel("dude", 15, 30000, projectsList));
		EmployeeModel matchedemployee = employeeInfo.stream().filter(employee -> employee.getE_id().equals(e_id)).findFirst()
				.orElse(null);

		EmployeeResponse standardResponse = new EmployeeResponse();

		if (matchedemployee != null) {
			GetEmployeeResponse dataResponse = new GetEmployeeResponse();
			dataResponse.setE_name(matchedemployee.getE_name());
			dataResponse.setE_projects(matchedemployee.getE_projects());
			dataResponse.setE_id(matchedemployee.getE_id());

			standardResponse.setResponseDescription("Successfully Retrieved");
			standardResponse.setStatus(true);
			standardResponse.setStatusCode(200);
			standardResponse.setGetEmployeeResponse(dataResponse);
		} else {
			standardResponse.setResponseDescription("Not Found");
			standardResponse.setStatus(false);
			standardResponse.setStatusCode(400);
		}

		return standardResponse;
	}
}


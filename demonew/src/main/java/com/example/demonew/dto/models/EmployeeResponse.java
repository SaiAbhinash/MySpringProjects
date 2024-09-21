package com.example.demonew.dto.models;

import com.example.demonew.dto.models.*;

public class EmployeeResponse {
	private Boolean status;
	private Integer statusCode;
	private String responseDescription;
	private GetEmployeeResponse getEmployeeResponse;
	public EmployeeResponse() {
		super();
		
	}
	public EmployeeResponse(Boolean status, Integer statusCode, String responseDescription, GetEmployeeResponse getEmployeeResponse) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.responseDescription = responseDescription;
		this.getEmployeeResponse = getEmployeeResponse;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponseDescription() {
		return responseDescription;
	}
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
	public GetEmployeeResponse getGetEmployeeResponse() {
		return getEmployeeResponse;
	}
	public void setGetEmployeeResponse(GetEmployeeResponse getEmployeeResponse) {
		this.getEmployeeResponse = getEmployeeResponse;
	}

}

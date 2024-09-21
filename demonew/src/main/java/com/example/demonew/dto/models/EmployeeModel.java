package com.example.demonew.dto.models;

import java.util.List;

public class EmployeeModel {
	private String e_name;
	private Integer e_id;
	private Integer e_salary;
	private List<String> e_projects;
	public EmployeeModel(String e_name, Integer e_id, Integer e_salary, List<String> projectsList) {
		super();
		this.e_name = e_name;
		this.e_id = e_id;
		this.e_salary = e_salary;
		this.e_projects = projectsList;
	}
	public EmployeeModel() {
		super();
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public Integer getE_id() {
		return e_id;
	}
	public void setE_id(Integer e_id) {
		this.e_id = e_id;
	}
	public Integer getE_salary() {
		return e_salary;
	}
	public void setE_salary(Integer e_salary) {
		this.e_salary = e_salary;
	}
	public List<String> getE_projects() {
		return e_projects;
	}
	public void setE_projects(List<String> e_projects) {
		this.e_projects = e_projects;
	}

}

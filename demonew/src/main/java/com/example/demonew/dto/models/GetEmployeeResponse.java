package com.example.demonew.dto.models;


import java.util.List;

public class GetEmployeeResponse {
	private String e_name;
	private List<String> e_projects;
	private Integer e_id;
	public GetEmployeeResponse() {
		super();
		
	}
	public GetEmployeeResponse(String e_name, List<String> e_projects, Integer e_id) {
		super();
		this.e_name = e_name;
		this.e_projects = e_projects;
		this.e_id = e_id;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public List<String> getE_projects() {
		return e_projects;
	}
	public void setE_projects(List<String> e_projects) {
		this.e_projects = e_projects;
	}
	public Integer getE_id() {
		return e_id;
	}
	public void setE_id(Integer e_id) {
		this.e_id = e_id;
	}
	
	

}

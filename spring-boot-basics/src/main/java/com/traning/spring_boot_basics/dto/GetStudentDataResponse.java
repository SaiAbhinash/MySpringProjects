package com.traning.spring_boot_basics.dto;

import java.util.List;

public class GetStudentDataResponse {

	private String studentName;
	private List<String> subjects;
	private Double courseFees;
	

	public GetStudentDataResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetStudentDataResponse(String studentName, List<String> subjects, Double courseFees) {
		super();
		this.studentName = studentName;
		this.subjects = subjects;
		this.courseFees = courseFees;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public Double getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(Double courseFees) {
		this.courseFees = courseFees;
	}

}

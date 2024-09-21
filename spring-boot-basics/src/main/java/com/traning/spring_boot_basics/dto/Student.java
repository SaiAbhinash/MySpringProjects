package com.traning.spring_boot_basics.dto;

import java.util.List;

public class Student {
	private String studentName;
	private Integer rollNo;
	private List<String> subjects;
	private Double courseFee;

	public Student() {

	}

	public Student(String studentName, Integer rollNo, List<String> subjects, Double courseFee) {
		this.studentName = studentName;
		this.rollNo = rollNo;
		this.subjects = subjects;
		this.courseFee = courseFee;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getRollNo() {
		return rollNo;
	}

	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public Double getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(Double courseFee) {
		this.courseFee = courseFee;
	}

}

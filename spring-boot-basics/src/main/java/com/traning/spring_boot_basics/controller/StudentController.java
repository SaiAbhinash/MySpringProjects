package com.traning.spring_boot_basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traning.spring_boot_basics.dto.StandardResponse;
import com.traning.spring_boot_basics.dto.Student;
import com.traning.spring_boot_basics.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public ResponseEntity<StandardResponse> addStudentInfo(@RequestBody Student student) {
		StandardResponse response = studentService.addStudentService(student);
		if (response.getStatus() == true) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/find-student/{rollNo}")
	public ResponseEntity<?> getStudentData(@PathVariable("rollNo") Integer rollNo) {
		StandardResponse studentDataResponse = studentService.getStudentData(rollNo);
		if (studentDataResponse.getStatus() == true) {
			return new ResponseEntity<>(studentDataResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(studentDataResponse, HttpStatus.NOT_FOUND);
		}

	}
}

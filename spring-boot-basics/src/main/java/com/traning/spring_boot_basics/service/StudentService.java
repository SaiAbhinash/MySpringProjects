package com.traning.spring_boot_basics.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.traning.spring_boot_basics.dto.GetStudentDataResponse;
import com.traning.spring_boot_basics.dto.StandardResponse;
import com.traning.spring_boot_basics.dto.Student;

@Service
public class StudentService {

	List<Student> studentInfo = new ArrayList<>();

	public StandardResponse addStudentService(Student student) {
		StandardResponse response = new StandardResponse();
		if (student != null) {
			Student studentTwo = new Student();
			studentTwo.setStudentName(student.getStudentName());
			studentTwo.setRollNo(student.getRollNo());
			studentTwo.setSubjects(student.getSubjects());
			studentTwo.setCourseFee(student.getCourseFee());

			studentInfo.add(studentTwo);

			response.setResponseDescription("Added Successfully");
			response.setStatus(true);
			response.setStatusCode(201);

		} else {
			response.setResponseDescription("Not Added Successfully");
			response.setStatus(false);
			response.setStatusCode(500);
		}
		return response;
	}

	public StandardResponse getStudentData(Integer rollNo) {
		List<String> subjectList = Arrays.asList("Telugu", "Hindi", "English");

		studentInfo.add(new Student("Sagar", 10, subjectList, 10.00));
		studentInfo.add(new Student("Krishna", 12, subjectList, 10.00));
		studentInfo.add(new Student("Prasad", 13, subjectList, 10.00));

		
		Student matchedStudent = studentInfo.stream()
				.filter(student -> student.getRollNo().equals(rollNo))
				.findFirst()
				.orElse(null);

		StandardResponse standardResponse = new StandardResponse();

		if (matchedStudent != null) {
			GetStudentDataResponse dataResponse = new GetStudentDataResponse();
			dataResponse.setStudentName(matchedStudent.getStudentName());
			dataResponse.setSubjects(matchedStudent.getSubjects());
			dataResponse.setCourseFees(matchedStudent.getCourseFee());

			standardResponse.setResponseDescription("Successfully Retrieved");
			standardResponse.setStatus(true);
			standardResponse.setStatusCode(200);
			standardResponse.setGetStudentDataResponse(dataResponse);
		} else {
			standardResponse.setResponseDescription("Not Found");
			standardResponse.setStatus(false);
			standardResponse.setStatusCode(400);
		}

		return standardResponse;

	}
}

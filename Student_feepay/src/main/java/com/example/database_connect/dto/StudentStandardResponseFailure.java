package com.example.database_connect.dto;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentStandardResponseFailure {
	private Integer statusCode;
	private boolean status;
	private String responseDescription;

}

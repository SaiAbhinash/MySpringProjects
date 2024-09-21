package com.example.database_connect.dto;

import java.util.List;

import lombok.Data;
@Data

public class StudentStandardReponseByEmail {
	private boolean status;
	private int statusCode;
	private String responseDescription;
	private List<ResponseByEmail> response;

}

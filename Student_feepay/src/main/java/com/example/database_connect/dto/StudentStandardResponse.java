package com.example.database_connect.dto;

import lombok.Data;
@Data
public class StudentStandardResponse {
	private Integer statusCode;
	private Boolean status;
	private String responseDescription;
	private String transactionId;
	private String studentName;     
	private String studentEmail;     
	private String studentPayType;  
	private Double studentAmount;  
	private String date;  
	

}

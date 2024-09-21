package com.example.database_connect.dto;
import lombok.Data;

@Data
public class GetStudentTransactionDetailResponse {
	private boolean status;
	private int statusCode;
	private String responseDescription;
	private FeepayRequest response;
}

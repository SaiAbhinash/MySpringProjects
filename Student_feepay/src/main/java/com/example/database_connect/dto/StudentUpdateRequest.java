package com.example.database_connect.dto;
import lombok.Data;

@Data
public class StudentUpdateRequest {
	private String transactionId;
	private String studentName;
	private String emailID;
	private String payType;
	private Double fees;
}

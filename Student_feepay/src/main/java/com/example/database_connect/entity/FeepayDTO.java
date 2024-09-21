package com.example.database_connect.entity;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "feepayment_service")
@Data
public class FeepayDTO {
	@Id
	private String transactionId;
	private String studentName;
	private String studentEmail;
	private String studentpayType;
	private Double studnetAmount;
	private String date;
}


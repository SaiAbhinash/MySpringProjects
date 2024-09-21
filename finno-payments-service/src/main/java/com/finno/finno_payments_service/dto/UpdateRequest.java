package com.finno.finno_payments_service.dto;

import lombok.Data;

@Data
public class UpdateRequest {
	private String transactionId;
	private String username;
	private String emailId;
	private String paymentType;
	private Double amount;
}

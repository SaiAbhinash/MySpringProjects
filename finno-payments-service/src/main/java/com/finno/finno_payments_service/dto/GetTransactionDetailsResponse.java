package com.finno.finno_payments_service.dto;

import lombok.Data;

@Data
public class GetTransactionDetailsResponse {
	private boolean status;
	private int statusCode;
	private String responseDescription;
	private PaymentRequest response;
}

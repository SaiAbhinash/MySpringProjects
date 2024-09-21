package com.finno.finno_payments_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentRequest {
	@NotNull(message = "user name is required")
	@Pattern(regexp = "^[a-zA-Z]+$",message = "please provide alphabets onlt")
	@Size(min = 10, max = 20, message = "size doesn't matched")
	private String username;
	@Email(message = "input is not a valid email")
	private String emailId;
	private String paymentType;
	private Double amount;
}

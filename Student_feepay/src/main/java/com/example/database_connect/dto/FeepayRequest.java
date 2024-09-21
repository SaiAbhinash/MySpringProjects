package com.example.database_connect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FeepayRequest {
	@NotNull(message = "user name is required")
	@Pattern(regexp = "^[a-zA-Z]+$",message = "please provide alphabets only")
	@Size(min = 10, max = 20, message = "size doesn't matched")
	private String studentName;
	@Email(message = "input is not a valid email")
	private String emailID;
	private String payType;
	@Min(value = 0L, message = "The value must be positive")
	@Positive
	private Double fees;
}

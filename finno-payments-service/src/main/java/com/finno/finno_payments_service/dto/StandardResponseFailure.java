package com.finno.finno_payments_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseFailure {
	private Integer statusCode;
	private boolean status;
	private String responseDescription;
}

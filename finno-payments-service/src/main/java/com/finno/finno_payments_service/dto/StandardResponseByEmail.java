package com.finno.finno_payments_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseByEmail {
	private boolean status;
	private int statusCode;
	private String responseDescription;
	private List<FindByEmailResponse> response;
}

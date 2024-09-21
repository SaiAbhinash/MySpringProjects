package com.example.database_connect.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
//JsonPropertyOrder({"","",})
public class ResponseByEmail {

		private Integer statusCode;
		private Boolean status;
		private String responseDescription;
		private String transactionId;
		@JsonProperty("name")
		private String studentName; 
		private String studentEmail;  
		@JsonProperty("payType")
		private String studentPayType;  
		private Double studentAmount;  
		private String date;  
}

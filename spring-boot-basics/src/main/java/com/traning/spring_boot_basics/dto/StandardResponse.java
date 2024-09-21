package com.traning.spring_boot_basics.dto;

public class StandardResponse {
	private Boolean status;
	private Integer statusCode;
	private String responseDescription;
	private GetStudentDataResponse getStudentDataResponse;

	public GetStudentDataResponse getGetStudentDataResponse() {
		return getStudentDataResponse;
	}

	public void setGetStudentDataResponse(GetStudentDataResponse getStudentDataResponse) {
		this.getStudentDataResponse = getStudentDataResponse;
	}

	public StandardResponse(Boolean status, Integer statusCode, String responseDescription,
			GetStudentDataResponse getStudentDataResponse) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.responseDescription = responseDescription;
		this.getStudentDataResponse = getStudentDataResponse;
	}

	public StandardResponse() {
		// TODO Auto-generated constructor stub
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

}

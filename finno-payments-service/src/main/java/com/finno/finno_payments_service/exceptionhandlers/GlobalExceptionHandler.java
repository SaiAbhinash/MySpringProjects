package com.finno.finno_payments_service.exceptionhandlers;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.finno.finno_payments_service.dto.StandardResponseFailure;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodValidationException(MethodValidationException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		StandardResponseFailure failure = new StandardResponseFailure();
		failure.setResponseDescription(ex.getMessage());
		failure.setStatus(false);
		failure.setStatusCode(400);
		return new ResponseEntity<>(failure, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		StandardResponseFailure failure = new StandardResponseFailure();

		String errorMsg = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(", "));

		failure.setResponseDescription(errorMsg);
		failure.setStatus(false);
		failure.setStatusCode(400);
		return new ResponseEntity<>(failure, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		StandardResponseFailure failure = new StandardResponseFailure();
		failure.setResponseDescription(ex.getMessage());
		failure.setStatus(false);
		failure.setStatusCode(ex.getStatusCode().value());
		return new ResponseEntity<>(failure, HttpStatus.METHOD_NOT_ALLOWED);
	}

}

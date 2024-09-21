package com.finno.finno_payments_service.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finno.finno_payments_service.controller.PaymentController;
import com.finno.finno_payments_service.dto.FindByEmailResponse;
import com.finno.finno_payments_service.dto.GetTransactionDetailsResponse;
import com.finno.finno_payments_service.dto.PaymentRequest;
import com.finno.finno_payments_service.dto.StandardResponse;
import com.finno.finno_payments_service.dto.StandardResponseByEmail;
import com.finno.finno_payments_service.dto.UpdateRequest;
import com.finno.finno_payments_service.entity.PaymentDTO;
import com.finno.finno_payments_service.repository.PaymentRepository;

@Service
public class PaymentService {
	private Logger logger = LoggerFactory.getLogger(PaymentService.class);
	@Autowired
	private PaymentRepository paymentRepository;

	public StandardResponse saveTransaction(PaymentRequest paymentRequest) {
		StandardResponse response = new StandardResponse();
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setTransaction_id(UUID.randomUUID().toString());
		paymentDTO.setUsername(paymentRequest.getUsername());
		paymentDTO.setEmail(paymentRequest.getEmailId());
		paymentDTO.setAmount(paymentRequest.getAmount());
		paymentDTO.setPayment_type(paymentRequest.getPaymentType());
		paymentDTO.setDate(getCurrentDate());

		PaymentDTO saveTrnsResponse = paymentRepository.save(paymentDTO);
		if (saveTrnsResponse.getTransaction_id() != null) {
			response.setResponseDescription("Transaction save successfully");
			response.setStatus(true);
			response.setStatusCode(201);
			response.setTransactionId(saveTrnsResponse.getTransaction_id());
			logger.info("success save api");
		} else {
			response.setResponseDescription("Unable save Transaction ");
			response.setStatus(false);
			response.setStatusCode(500);
			logger.error("Save api call  failed internal exception ");
		}
		return response;
	}

	private String getCurrentDate() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		return ts.toString();
	}

	public GetTransactionDetailsResponse getTransactionDetails(String transactionId) {
		GetTransactionDetailsResponse detailsResponse = new GetTransactionDetailsResponse();
		PaymentRequest paymentRequest = new PaymentRequest();
		Optional<PaymentDTO> response = paymentRepository.findById(transactionId);
		if (response.isPresent()) {
			paymentRequest.setAmount(response.get().getAmount());
			paymentRequest.setEmailId(response.get().getEmail());
			paymentRequest.setPaymentType(response.get().getPayment_type());
			paymentRequest.setUsername(response.get().getUsername());
			detailsResponse.setResponse(paymentRequest);
			detailsResponse.setStatus(true);
			detailsResponse.setResponseDescription("Successfully Able to retireve data");
			detailsResponse.setStatusCode(200);
		} else {
			detailsResponse.setStatus(false);
			detailsResponse.setResponseDescription("NOT_FOUND");
			detailsResponse.setStatusCode(404);
		}
		return detailsResponse;
	}

	public StandardResponse updateCustomerDetails(UpdateRequest request) {
		StandardResponse standardResponse = new StandardResponse();
		String transactionId = request.getTransactionId();
		PaymentDTO response = paymentRepository.findById(transactionId).get();
		if (request.getUsername() != null) {
			response.setUsername(request.getUsername());
		}
		if (request.getEmailId() != null) {
			response.setEmail(request.getEmailId());
		}
		if (request.getPaymentType() != null) {
			response.setPayment_type(request.getEmailId());
		}
		if (request.getAmount() != null) {
			response.setAmount(request.getAmount());
		}

		try {
			paymentRepository.save(response);
			standardResponse.setResponseDescription("Updated Successfully");
			standardResponse.setStatus(true);
			standardResponse.setStatusCode(200);
		} catch (Exception e) {
			standardResponse.setStatus(false);
			standardResponse.setResponseDescription(e.getMessage());
			standardResponse.setStatusCode(500);
			logger.error("Exception occured while updating customer details " + e.getMessage());
		}

		return standardResponse;
	}

//	public StandardResponseByEmail getByEmail(String emailId) {
//		StandardResponseByEmail response = new StandardResponseByEmail();
//		FindByEmailResponse emailResponse = new FindByEmailResponse();
//		PaymentDTO paymentResponse = paymentRepository.findByEmail(emailId);
//		if (paymentResponse != null) {
//			emailResponse.setTransactionId(paymentResponse.getTransaction_id());
//			emailResponse.setUsername(paymentResponse.getUsername());
//			emailResponse.setEmailId(paymentResponse.getEmail());
//			emailResponse.setAmount(paymentResponse.getAmount());
//			emailResponse.setPaymentType(paymentResponse.getPayment_type());
//			emailResponse.setDate(paymentResponse.getDate());
//			response.setResponse(emailResponse);
//			response.setStatus(true);
//			response.setResponseDescription("Successfully Able to retireve data");
//			response.setStatusCode(200);
//		} else {
//			response.setStatus(false);
//			response.setResponseDescription("NOT_FOUND");
//			response.setStatusCode(404);
//		}
//		return response;
//	}

	public StandardResponseByEmail getByPaymentType(String paymentType) {
		StandardResponseByEmail response = new StandardResponseByEmail();
		List<PaymentDTO> findByPaymentType = paymentRepository.findByPaymentType(paymentType);
		System.out.println(findByPaymentType);
		List<FindByEmailResponse> paymentRes = new ArrayList<>();
		for (PaymentDTO pay : findByPaymentType) {
			FindByEmailResponse emailResponse = new FindByEmailResponse();
			emailResponse.setTransactionId(pay.getTransaction_id());
			emailResponse.setUsername(pay.getUsername());
			emailResponse.setEmailId(pay.getEmail());
			emailResponse.setAmount(pay.getAmount());
			emailResponse.setPaymentType(pay.getPayment_type());
			emailResponse.setDate(pay.getDate());
			paymentRes.add(emailResponse);
		}

		response.setResponse(paymentRes);
		response.setStatus(true);
		response.setResponseDescription("Successfully Able to retireve data");
		response.setStatusCode(200);
		return response;
	}

}

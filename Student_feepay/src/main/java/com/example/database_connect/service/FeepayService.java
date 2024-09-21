package com.example.database_connect.service;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Response;
import org.springframework.stereotype.Service;

import com.example.database_connect.repository.FeepayRepository;
import com.example.database_connect.dto.*;
import com.example.database_connect.entity.FeepayDTO;

@Service
public class FeepayService {
	private static final Logger logger = LogManager.getLogger(FeepayService.class);
	@Autowired
	FeepayRepository feepayrepository;
	public StudentStandardResponse savetransactionpayment(FeepayRequest feepayrequest) {
		StudentStandardResponse payresponse = new StudentStandardResponse();
		FeepayDTO dtorequest=new FeepayDTO();
		dtorequest.setTransactionId(UUID.randomUUID().toString());
		dtorequest.setStudentName(feepayrequest.getStudentName());
		dtorequest.setStudentEmail(feepayrequest.getEmailID());
		dtorequest.setStudentpayType(feepayrequest.getPayType());
		dtorequest.setStudnetAmount(feepayrequest.getFees());
		dtorequest.setDate(getCurrentDate());
		
		
		FeepayDTO saveTrnsResponse = feepayrepository.save(dtorequest);
		if (saveTrnsResponse.getTransactionId() != null) {
			payresponse.setResponseDescription("Transaction save successfully");
			payresponse.setStatus(true);
			payresponse.setStatusCode(201);
			payresponse.setTransactionId(saveTrnsResponse.getTransactionId());
		} else {
			payresponse.setResponseDescription("Unable save Transaction ");
			payresponse.setStatus(false);
			payresponse.setStatusCode(500);
		}
		
		return payresponse;
	}
	public StudentStandardResponse getTransactionByID(String transactionId) {
		StudentStandardResponse getresponse= new StudentStandardResponse();
		//FeepayRequest feepaymentRequest = new FeepayRequest();
		Optional<FeepayDTO> payment = feepayrepository.findById(transactionId);
		if (payment.isPresent()) {
			FeepayDTO feepayDTO=payment.get();
            getresponse.setStatus(true);
            getresponse.setStatusCode(200);
            getresponse.setResponseDescription("Transaction ID details Found Successfully");
            getresponse.setTransactionId(feepayDTO.getTransactionId());
            getresponse.setStudentName(feepayDTO.getStudentName());
            getresponse.setStudentEmail(feepayDTO.getStudentEmail());
            getresponse.setStudentPayType(feepayDTO.getStudentpayType());
            getresponse.setStudentAmount(feepayDTO.getStudnetAmount());
            getresponse.setDate(feepayDTO.getDate());
            
        } else {
        	getresponse.setStatus(false);
        	getresponse.setStatusCode(404);
        	getresponse.setResponseDescription("Transaction not found");
        }
        return getresponse;
		
	}
	public  StudentStandardResponse updateStudentDetails(StudentUpdateRequest request) {
		StudentStandardResponse standardResponse = new StudentStandardResponse();
		String transactionId = request.getTransactionId();
		FeepayDTO response = feepayrepository.findById(transactionId).get();
		
		if (request.getStudentName() != null) {
			response.setStudentName(request.getStudentName());
		}
		if (request.getEmailID() != null) {
			response.setStudentEmail(request.getEmailID());
		}
		if (request.getPayType() != null) {
			response.setStudentpayType(transactionId);
		}
		if (request.getFees() != null) {
			response.setStudnetAmount(null);
		}

		try {
			feepayrepository.save(response);
			standardResponse.setResponseDescription("Updated Successfully");
			standardResponse.setStatus(true);
			standardResponse.setStatusCode(200);
			logger.info("Request Updated Successfully");
		} catch (Exception e) {
			standardResponse.setStatus(false);
			standardResponse.setResponseDescription(e.getMessage());
			standardResponse.setStatusCode(500);
			logger.error("Exception occured while updating customer details " + e.getMessage());
		}

		return standardResponse;
	}
	
	
	
	public ResponseByEmail getResponseByemail(String studentEmail) {
		ResponseByEmail getemailresponse=new ResponseByEmail();
		Optional<FeepayDTO> email = feepayrepository.findByStudentEmail(studentEmail);
		if(email.isPresent()) {
			FeepayDTO feepayDTO=email.get();
			getemailresponse.setStatus(true);
			getemailresponse.setStatusCode(200);
			getemailresponse.setResponseDescription("EMail ID Found Successfully");
			getemailresponse.setTransactionId(feepayDTO.getTransactionId());
			getemailresponse.setStudentName(feepayDTO.getStudentName());
			getemailresponse.setStudentEmail(feepayDTO.getStudentEmail());
			getemailresponse.setStudentPayType(feepayDTO.getStudentpayType());
            getemailresponse.setStudentAmount(feepayDTO.getStudnetAmount());
            getemailresponse.setDate(feepayDTO.getDate());
			
			
		}else {
			getemailresponse.setStatus(false);
			getemailresponse.setStatusCode(404);
        	getemailresponse.setResponseDescription("EmailID not found");
        }
		return getemailresponse;
		
		
	}
	
	public StudentStandardReponseByEmail getByPaymentType(String paymentType) {
		StudentStandardReponseByEmail response = new StudentStandardReponseByEmail();
		List<FeepayDTO> findByPaymentType = feepayrepository.findByPaymentType(paymentType);
		System.out.println(findByPaymentType);
		List<ResponseByEmail> paymentRes = new ArrayList<>();
		for (FeepayDTO pay : findByPaymentType) {
			ResponseByEmail emailResponse = new ResponseByEmail();
			emailResponse.setTransactionId(pay.getTransactionId());
			emailResponse.setStudentName(pay.getStudentName());
			emailResponse.setStudentEmail(pay.getStudentEmail());
			emailResponse.setStudentPayType(pay.getStudentpayType());
			emailResponse.setStudentAmount(pay.getStudnetAmount());
			emailResponse.setDate(pay.getDate());
			paymentRes.add(emailResponse);
		}

		response.setResponse(paymentRes);
		response.setStatus(true);
		response.setResponseDescription("Successfully Able to retireve data");
		response.setStatusCode(200);
		return response;
	}
	

	public StudentStandardResponse deleteTransactionById(String transactionId) {
        StudentStandardResponse response = new StudentStandardResponse();
        Optional<FeepayDTO> payment = feepayrepository.findById(transactionId);

        if (payment.isPresent()) {
            feepayrepository.deleteById(transactionId);
            response.setStatus(true);
            response.setStatusCode(200);
            response.setResponseDescription("Transaction deleted successfully");
        } else {
            response.setStatus(false);
            response.setStatusCode(404);
            response.setResponseDescription("Transaction not found");
        }
        return response;
    }
	
	private String getCurrentDate() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		return ts.toString();
	}
}

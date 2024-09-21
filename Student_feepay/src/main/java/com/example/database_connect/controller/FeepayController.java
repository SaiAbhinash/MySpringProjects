package com.example.database_connect.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.database_connect.dto.FeepayRequest;
import com.example.database_connect.dto.ResponseByEmail;
import com.example.database_connect.dto.StudentStandardReponseByEmail;
import com.example.database_connect.dto.StudentStandardResponse;
import com.example.database_connect.dto.StudentUpdateRequest;
import com.example.database_connect.service.FeepayService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/studnet-payment")
public class FeepayController {
	private static final Logger logger = LogManager.getLogger(FeepayController.class);
	@Autowired
	private FeepayService feepayservice;
	
	@PostMapping("/savepayment")
	@Tag(name = "save", description = "save transaction of payment gateway")
	public ResponseEntity<StudentStandardResponse>saveTransaction(@RequestBody @Valid FeepayRequest feepayrequest){
		StudentStandardResponse saveTransaction = feepayservice.savetransactionpayment(feepayrequest);
		if (saveTransaction.getStatus()) {
			logger.info("New Student Transaction is created.");
			return new ResponseEntity<>(saveTransaction, HttpStatus.CREATED);
		} else {
			logger.error("Student Was not saved due to internal server error.");
			return new ResponseEntity<>(saveTransaction, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getpayment/{transactionId}")
	public ResponseEntity<StudentStandardResponse> getTransaction(@PathVariable String transactionId){
		StudentStandardResponse getTransaction = feepayservice.getTransactionByID(transactionId);
		if(getTransaction.getStatus()) {
			logger.info("Transaction ID with " +transactionId+ " is fetched from database");
			return new ResponseEntity<>(getTransaction, HttpStatus.OK);
        } else {
        	logger.error("Transaction ID " +transactionId+ " which your enter was not found , Please try again.");
            return new ResponseEntity<>(getTransaction, HttpStatus.NOT_FOUND);
        }
	}
	
	
	
	
	@GetMapping("/find-by-email/{studentEmail}")
	public ResponseEntity<ResponseByEmail> getEmail(@PathVariable String studentEmail) {
	    ResponseByEmail emailResponse = feepayservice.getResponseByemail(studentEmail);
	    if (emailResponse.getStatus()) {
	        logger.info("Transaction details for student email " + studentEmail + " fetched from database");
	        return new ResponseEntity<>(emailResponse, HttpStatus.OK);
	    } else {
	        logger.error("Transaction details for student email " + studentEmail + " not found, please try again.");
	        return new ResponseEntity<>(emailResponse, HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/find-by-payment/{paymentType}")
    public ResponseEntity<StudentStandardReponseByEmail> findPaymentMethod(@PathVariable("paymentType") String paymentType) {
		StudentStandardReponseByEmail byEmail = feepayservice.getByPaymentType(paymentType);
        if (byEmail.isStatus()) {
        	logger.info("Payment Type details for student " + paymentType + " is fetched from database");
            return new ResponseEntity<>(byEmail, HttpStatus.OK);
        } else {
        	logger.error("Payment Type details for student " + paymentType + " is not found, please try again.");
            return new ResponseEntity<>(byEmail, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@PutMapping("/update-student-details")
    public ResponseEntity<StudentStandardResponse> updateDetails(@RequestBody StudentUpdateRequest updateSreq) {
		StudentStandardResponse savetransaction = feepayservice.updateStudentDetails(updateSreq);
        if (savetransaction.getStatus()) {
        	logger.info("Student Deatils were updated successfully");
            return new ResponseEntity<>(savetransaction, HttpStatus.OK);
        } else {
        	logger.error("Updating was failed due to internal error , check again");
            return new ResponseEntity<>(savetransaction, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

	
	@DeleteMapping("/deletepayment/{transactionId}")
    public ResponseEntity<StudentStandardResponse> deleteTransaction(@PathVariable String transactionId) {
        StudentStandardResponse deleteTransaction = feepayservice.deleteTransactionById(transactionId);
        if (deleteTransaction.getStatus()) {
        	logger.info("Transaction ID " +transactionId+ " was deleted succesfully from database");
            return new ResponseEntity<>(deleteTransaction, HttpStatus.OK);
        } else {
        	logger.error("Transaction ID " +transactionId+ " which your enter was not found , Please try again.");
            return new ResponseEntity<>(deleteTransaction, HttpStatus.NOT_FOUND);
        }
    }

}

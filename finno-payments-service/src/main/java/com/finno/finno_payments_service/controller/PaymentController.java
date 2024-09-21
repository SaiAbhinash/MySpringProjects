package com.finno.finno_payments_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finno.finno_payments_service.dto.GetTransactionDetailsResponse;
import com.finno.finno_payments_service.dto.PaymentRequest;
import com.finno.finno_payments_service.dto.StandardResponse;
import com.finno.finno_payments_service.dto.StandardResponseByEmail;
import com.finno.finno_payments_service.dto.UpdateRequest;
import com.finno.finno_payments_service.service.PaymentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment-gateway")
public class PaymentController {

    private Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    @Tag(name = "save", description = "save transaction of payment gateway")
    public ResponseEntity<StandardResponse> saveTransaction(@RequestBody @Valid PaymentRequest paymentRequest) {
        logger.info("save api call started with ");
        StandardResponse saveTransaction = paymentService.saveTransaction(paymentRequest);
        boolean status = saveTransaction.isStatus();
        if (status) {
            logger.info("Save method exectued " + saveTransaction.getTransactionId());
            return new ResponseEntity<>(saveTransaction, HttpStatus.CREATED);
        } else {
            logger.error("Save api call  failed internal exception ");
            return new ResponseEntity<>(saveTransaction, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/find-by-id/{transactionId}")
    public ResponseEntity<GetTransactionDetailsResponse> findTransactionDetails(
            @PathVariable("transactionId") String transactionId) {
        logger.info("find-by-id call started with {}", transactionId);
        GetTransactionDetailsResponse transactionDetails = paymentService.getTransactionDetails(transactionId);
        if (transactionDetails.isStatus()) {
            logger.info("find-by-id call succeeded with {}", transactionId);
            return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
        } else {
            logger.error("find-by-id call failed with {}", transactionId);
            return new ResponseEntity<>(transactionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-details")
    public ResponseEntity<StandardResponse> updateDetails(@RequestBody UpdateRequest updateReq) {
        StandardResponse saveTransaction = paymentService.updateCustomerDetails(updateReq);
        if (saveTransaction.isStatus()) {
            return new ResponseEntity<>(saveTransaction, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(saveTransaction, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//	@GetMapping("/find-by-email/{emailId}")
//	public ResponseEntity<StandardResponseByEmail> findTrnsactionbyEmail(@PathVariable("emailId") String emailId) {
//		StandardResponseByEmail byEmail = paymentService.getByEmail(emailId);
//		if (byEmail.isStatus()) {
//			return new ResponseEntity<>(byEmail, HttpStatus.CREATED);
//		} else {
//			return new ResponseEntity<>(byEmail, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

    @GetMapping("/find-by-payment/{paymentType}")
    public ResponseEntity<StandardResponseByEmail> findPaymentMethod(@PathVariable("paymentType") String paymentType) {
        StandardResponseByEmail byEmail = paymentService.getByPaymentType(paymentType);
        if (byEmail.isStatus()) {
            return new ResponseEntity<>(byEmail, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(byEmail, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

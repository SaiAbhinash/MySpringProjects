package com.example.database_connect.controller;

import com.example.database_connect.dto.FeepayRequest;
import com.example.database_connect.dto.ResponseByEmail;
import com.example.database_connect.dto.StudentStandardResponse;
import com.example.database_connect.service.FeepayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FeepayControllerTest {

    @Mock
    private FeepayService feepayService;

    @InjectMocks
    private FeepayController feepayController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTransaction() {
        FeepayRequest request = new FeepayRequest();
        request.setStudentName("sai");
        request.setEmailID("sai@example.com");
        request.setPayType("UPI");
        request.setFees(33000.00);

        StudentStandardResponse response = new StudentStandardResponse();
        response.setStatus(true);
        response.setTransactionId("1234");

        when(feepayService.savetransactionpayment(any(FeepayRequest.class))).thenReturn(response);

        ResponseEntity<StudentStandardResponse> result = feepayController.saveTransaction(request);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("1234", result.getBody().getTransactionId());
    }

    @Test
    void testGetTransaction() {
        String transactionId = "1234";
        StudentStandardResponse response = new StudentStandardResponse();
        response.setStatus(true);
        response.setTransactionId(transactionId);

        when(feepayService.getTransactionByID(transactionId)).thenReturn(response);

        ResponseEntity<StudentStandardResponse> result = feepayController.getTransaction(transactionId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(transactionId, result.getBody().getTransactionId());
    }

    @Test
    void testGetEmail() {
        String email = "sai@example.com";
        ResponseByEmail response = new ResponseByEmail();
        response.setStatus(true);
        response.setStudentEmail(email);

        when(feepayService.getResponseByemail(email)).thenReturn(response);

        ResponseEntity<ResponseByEmail> result = feepayController.getEmail(email);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(email, result.getBody().getStudentEmail());
    }
}

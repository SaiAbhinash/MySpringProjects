package com.example.database_connect.service;

import com.example.database_connect.dto.FeepayRequest;
import com.example.database_connect.dto.StudentStandardResponse;
import com.example.database_connect.entity.FeepayDTO;
import com.example.database_connect.repository.FeepayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FeepayServiceTest {

    @Mock
    private FeepayRepository feepayRepository;

    @InjectMocks
    private FeepayService feepayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTransactionPayment() {
        FeepayRequest request = new FeepayRequest();
        request.setStudentName("John Doe");
        request.setEmailID("john@example.com");
        request.setPayType("Credit Card");
        request.setFees(1500.00);

        FeepayDTO feepayDTO = new FeepayDTO();
        feepayDTO.setTransactionId("1234");

        when(feepayRepository.save(any(FeepayDTO.class))).thenReturn(feepayDTO);

        StudentStandardResponse response = feepayService.savetransactionpayment(request);
        assertEquals(true, response.getStatus());
        assertEquals("1234", response.getTransactionId());
    }

    @Test
    void testGetTransactionByID() {
        String transactionId = "1234";
        FeepayDTO feepayDTO = new FeepayDTO();
        feepayDTO.setTransactionId(transactionId);

        when(feepayRepository.findById(transactionId)).thenReturn(Optional.of(feepayDTO));

        StudentStandardResponse response = feepayService.getTransactionByID(transactionId);
        assertEquals(true, response.getStatus());
        assertEquals(transactionId, response.getTransactionId());
    }
}

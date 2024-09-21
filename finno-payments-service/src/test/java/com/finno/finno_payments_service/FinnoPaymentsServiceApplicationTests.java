package com.finno.finno_payments_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finno.finno_payments_service.dto.StandardResponseByEmail;
import com.finno.finno_payments_service.service.PaymentService;

@SpringBootTest
class FinnoPaymentsServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	PaymentService paymentService;

	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	String jsonResponse = """
						{
			    "status": true,
			    "statusCode": 200,
			    "responseDescription": "Successfully Able to retireve data",
			    "response": [
			        {
			            "transactionId": "1",
			            "username": "Jhon",
			            "emailId": "jhon@gmail.com",
			            "paymentType": "UPI",
			            "amount": 1234.0,
			            "date": "2024-09-02 22:48:48.604"
			        },
			        {
			            "transactionId": "12207566-41ab-4b0d-85c5-5415b7ce9e3c",
			            "username": "kumar",
			            "emailId": "kumar@gmail.com",
			            "paymentType": "UPI",
			            "amount": 1098.0,
			            "date": "2024-09-02 22:57:22.794"
			        }
			    ]
			}
						""";

	@Test
	void testFindByPaymentType() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			StandardResponseByEmail mockResponse1 = mapper.readValue(jsonResponse, StandardResponseByEmail.class);
			StandardResponseByEmail mockResponse2 = paymentService.getByPaymentType("UPI");
			when(mockResponse2).thenReturn(mockResponse1);
			assertEquals(mockResponse1.getStatusCode(), mockResponse2.getStatusCode());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}

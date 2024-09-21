package com.finno.finno_payments_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payment_service")
@Data
public class PaymentDTO {
	@Id
	private String transaction_id;
	private String username;
	private String email;
	private String payment_type;
	private Double amount;
	private String date;
}

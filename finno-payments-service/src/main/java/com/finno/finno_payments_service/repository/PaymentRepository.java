package com.finno.finno_payments_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finno.finno_payments_service.entity.PaymentDTO;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentDTO, String> {

	PaymentDTO findByEmail(String email);

	@Query(nativeQuery = true, value = "SELECT * FROM payment_service WHERE payment_type = ?1")
    List<PaymentDTO> findByPaymentType(String paymentType);

}

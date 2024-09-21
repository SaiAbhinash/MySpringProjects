package com.example.database_connect.repository;

import java.util.List;
import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.database_connect.entity.FeepayDTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeepayRepository extends CrudRepository<FeepayDTO, String>{
	Optional<FeepayDTO> findByStudentEmail(String studentEmail);

//	List<FeepayDTO> findByPaymentType(String paymentType);
//	FeepayDTO findByEmail(String email);
	
	//FeepayDTO findByEmail(String email);

	@Query(nativeQuery = true, value = "SELECT * FROM feepay_student.feepayment_service WHERE studentpay_type = ?1")
    List<FeepayDTO> findByPaymentType(String paymentType);

}




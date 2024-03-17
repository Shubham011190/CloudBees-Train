package com.Shubham.CloudBees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Shubham.CloudBees.entity.Receipt;
import com.Shubham.CloudBees.entity.UserData;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long>{
	
	@Query("SELECT r FROM Receipt r where r.user=?1")
	Receipt getReceiptFromUser(UserData user);
}

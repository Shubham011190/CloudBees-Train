package com.Shubham.CloudBees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Shubham.CloudBees.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

	@Query("SELECT s from Seat s where s.seatNo=?1")
	Seat getSeatFromNo(int seatNo);
}

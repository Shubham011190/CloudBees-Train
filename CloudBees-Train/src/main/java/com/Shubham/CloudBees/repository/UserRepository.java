package com.Shubham.CloudBees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Shubham.CloudBees.entity.Seat;
import com.Shubham.CloudBees.entity.UserData;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long>{

	@Query("SELECT u from UserData u WHERE u.seat.section =?1")
	List<UserData> findAllUsersWithSection(String section);
	
	@Query("SELECT u FROM UserData u WHERE u.firstName=?1 AND u.lastName=?2")
	UserData getUserFromName(String firstName, String lastName);
	
	@Transactional
    default void updateSeatForUser(UserData user, Seat newSeat) {
        user.setSeat(newSeat);
        save(user);
    }

	UserData findUserBySeatSeatNo(int seatNo);
	
}

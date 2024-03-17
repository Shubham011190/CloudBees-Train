package com.Shubham.CloudBees.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shubham.CloudBees.entity.Receipt;
import com.Shubham.CloudBees.entity.Seat;
import com.Shubham.CloudBees.entity.UserData;
import com.Shubham.CloudBees.repository.ReceiptRepository;
import com.Shubham.CloudBees.repository.SeatRepository;
import com.Shubham.CloudBees.repository.UserRepository;

@Service
public class DaoService{

	@Autowired
	ReceiptRepository receiptRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SeatRepository seatRepository;

	public String saveReceipt(Receipt receipt) {
		// TODO Auto-generated method stub
		receiptRepository.save(receipt);
		return "Receipt saved in DB for User : " + receipt.getUser().getFirstName() + " "+ receipt.getUser().getLastName();
	}

	public List<Receipt> findAllReceipts() {
		// TODO Auto-generated method stub
		return receiptRepository.findAll();
	}
	
	public UserData saveUserData(UserData data) {
		return userRepository.save(data);
	}
	
	public Seat saveSeat(Seat seat) {
		return seatRepository.save(seat);
	}
	public void removeSeat(Seat seat) {
		seatRepository.delete(seat);
	}
	
	public List<UserData> getUsersFromSection(String section){
		return userRepository.findAllUsersWithSection(section.toUpperCase());
	}
	
	public UserData getUserFromName(String firstName, String lastName) {
		return userRepository.getUserFromName(firstName, lastName);
	}
	
	public void deleteUser(UserData data) {
		userRepository.delete(data);
	}
	
	public void deleteReceipt(UserData data) {
		receiptRepository.delete(getReceipt(data));
	}
	
	public Receipt getReceipt(UserData data) {
		return receiptRepository.getReceiptFromUser(data);
	}
	public void updateSeat(UserData user, Seat newSeat) {
		userRepository.updateSeatForUser(user, newSeat);
	}
	
	public List<Seat> getSeats(){
		return seatRepository.findAll();
	}
	public Seat getSeatfromNo(int seatNo) {
		return seatRepository.getSeatFromNo(seatNo);
	}
	
	public UserData getUserFromSeat(int seatNo) {
		return userRepository.findUserBySeatSeatNo(seatNo);
	}
	
}

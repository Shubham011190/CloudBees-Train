package com.Shubham.CloudBees.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shubham.CloudBees.dao.DaoService;
import com.Shubham.CloudBees.entity.Receipt;
import com.Shubham.CloudBees.entity.Seat;
import com.Shubham.CloudBees.entity.UserData;

@Service
public class TrainService {

	@Autowired
	DaoService daoService;

	public static Set<Seat> bookedSeats = new HashSet<>();
	private static int currentSeat = 0;


	//Saving receipt to the DB
	public Receipt saveReceiptData(UserData data) {
		// TODO Auto-generated method stub
		assignSeatToUser(data);
		daoService.saveUserData(data);
		Receipt receipt = new Receipt();
		receipt.setUser(data);
		daoService.saveReceipt(receipt);
		return receipt;
	}

	//Finding all the Receipts from the DB
	public List<Receipt> findAllReceipts() {
		// TODO Auto-generated method stub
		return daoService.findAllReceipts();
	}

	//Assigned a seat to the User
	public UserData assignSeatToUser(UserData user) {
		Seat seat = getNextSeat();
		boolean flag=false;
		UserData ifexist = daoService.getUserFromSeat(seat.getSeatNo());
		while(flag==false) {
			if(ifexist==null) {
				flag=true;
				break;
			}
			seat=getNextSeat();
			ifexist = daoService.getUserFromSeat(seat.getSeatNo());
		}
		
		user.setSeat(seat);
		bookedSeats.add(seat);
		System.out.println("Seat " + seat.getSection() + seat.getSeatNo() + " assigned to User : " + user.getFirstName() + " "+ user.getLastName());
		daoService.saveSeat(seat);
		return user;

	}

	//Getting Users within a given section
	public List<UserData> getUsersFromSection(String section){
		return daoService.getUsersFromSection(section);
	}

	//Removing User from Train
	public void deleteUserFromTrain(String userName) {
		String[] userSplitName = userName.split(" ");
		UserData data = daoService.getUserFromName(userSplitName[0], userSplitName[1]);
		if(data==null) {
			System.out.println("User does not exist!");
		}
		emptySeatFromUser(data);


	}

	//Modifying seat for the User
	public Receipt updateSeatForUser(String input) {
		String[] userSplitName = input.split(" ");
		UserData user = daoService.getUserFromName(userSplitName[0], userSplitName[1]);
		int newSeat = Integer.parseInt(userSplitName[2]);
		Seat seat = new Seat(findSectionFromSeat(newSeat), newSeat);
		if(daoService.getSeatfromNo(seat.getSeatNo())!=null) {
			System.out.println("Seat already occupied!");
			return null;
		}
		bookedSeats.add(seat);
		daoService.saveSeat(seat);
		daoService.updateSeat(user, seat);
		Receipt receipt = daoService.getReceipt(user);
		return receipt;
	}


	//Removing seat and all related entities and from the DB
	private void emptySeatFromUser(UserData user) {
		if(user.getSeat()!=null) {
			Seat seat = user.getSeat();
			bookedSeats.remove(seat);
			daoService.deleteReceipt(user);
			daoService.deleteUser(user);
			daoService.removeSeat(seat);
		}
	}

	//Calculating section based on seat
	public String findSectionFromSeat(int currentSeat) {
		int quotient = currentSeat/3;
		int rem = currentSeat%3;
		if((quotient==0 && rem!=0) || (quotient%2==0 && rem!=0) || (quotient%2==1 && rem==0)) {
			return "A";
		} else if((quotient%2==1 && rem!=0) || (quotient%2==0 && rem==0)) {
			return "B";
		}
		return null;
	}


	//Finding the next seat in order
	public Seat getNextSeat() {
		if(currentSeat == 61) {
			currentSeat=1;
		}
		if(bookedSeats.size()==60) {
			return null;
		}
		currentSeat++;
		Seat seat = new Seat(findSectionFromSeat(currentSeat), currentSeat);
		return seat;
	}

}

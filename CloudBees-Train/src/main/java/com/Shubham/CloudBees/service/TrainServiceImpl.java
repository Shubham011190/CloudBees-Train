package com.Shubham.CloudBees.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shubham.CloudBees.dao.TrainDAO;
import com.Shubham.CloudBees.entity.Receipt;
import com.Shubham.CloudBees.entity.Seat;
import com.Shubham.CloudBees.entity.UserData;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	TrainDAO trainDao;

	public static Set<Seat> bookedSeats = new HashSet<>();
	private static int currentSeat = 0;


	//Saving receipt to the DB
	public Receipt saveReceiptData(UserData data) {
		// TODO Auto-generated method stub
		assignSeatToUser(data);
		trainDao.saveUserData(data);
		Receipt receipt = new Receipt();
		receipt.setUser(data);
		trainDao.saveReceipt(receipt);
		return receipt;
	}

	//Finding all the Receipts from the DB
	public List<Receipt> findAllReceipts() {
		// TODO Auto-generated method stub
		return trainDao.findAllReceipts();
	}

	//Assigned a seat to the User
	public void assignSeatToUser(UserData user) {
		Seat seat = getNextSeat();
		boolean flag=false;
		UserData ifexist = trainDao.getUserFromSeat(seat.getSeatNo());
		while(flag==false) {
			if(ifexist==null) {
				flag=true;
				break;
			}
			seat=getNextSeat();
			ifexist = trainDao.getUserFromSeat(seat.getSeatNo());
		}
		
		user.setSeat(seat);
		bookedSeats.add(seat);
		System.out.println("Seat " + seat.getSection() + seat.getSeatNo() + " assigned to User : " + user.getFirstName() + " "+ user.getLastName());
		trainDao.saveSeat(seat);

	}

	//Getting Users within a given section
	public List<UserData> getUsersFromSection(String section){
		return trainDao.getUsersFromSection(section);
	}

	//Removing User from Train
	public String deleteUserFromTrain(String userName) {
		String[] userSplitName = userName.split(" ");
		UserData data = trainDao.getUserFromName(userSplitName[0], userSplitName[1]);
		if(data==null) {
			System.out.println("User does not exist!");
			return null;
		}
		emptySeatFromUser(data);
		return "User : " + userName + " removed from the Train!";

	}

	//Modifying seat for the User
	public Receipt updateSeatForUser(String input) {
		String[] userSplitName = input.split(" ");
		UserData user = trainDao.getUserFromName(userSplitName[0], userSplitName[1]);
		int newSeat = Integer.parseInt(userSplitName[2]);
		Seat seat = new Seat(findSectionFromSeat(newSeat), newSeat);
		if(trainDao.getSeatFromSeatNo(seat.getSeatNo())!=null) {
			System.out.println("Seat already occupied!");
			return null;
		}
		bookedSeats.add(seat);
		trainDao.saveSeat(seat);
		trainDao.updateSeat(user, seat);
		return trainDao.getReceipt(user);
	}


	//Removing seat and all related entities and from the DB
	private void emptySeatFromUser(UserData user) {
		if(user.getSeat()!=null) {
			Seat seat = user.getSeat();
			bookedSeats.remove(seat);
			trainDao.deleteReceipt(user);
			trainDao.deleteUser(user);
			trainDao.removeSeat(seat);
		}
	}

	//Calculating section based on seat. 
	public String findSectionFromSeat(int currentSeat) {
		int quotient = currentSeat/3;
		int rem = currentSeat%3;
		if((quotient%2==0 && rem!=0) || (quotient%2==1 && rem==0)) {
			return "A";
		} else {
			return "B";
		}
	}


	//Finding the next seat in order
	private Seat getNextSeat() {
		if(currentSeat == 61) {
			currentSeat=1;
		}
		if(bookedSeats.size()==60) {
			return null;
		}
		currentSeat++;
		return new Seat(findSectionFromSeat(currentSeat), currentSeat);

	}

}

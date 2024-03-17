package com.Shubham.CloudBees.service;

import java.util.List;

import com.Shubham.CloudBees.entity.Receipt;
import com.Shubham.CloudBees.entity.UserData;

public interface TrainService {

	Receipt saveReceiptData(UserData data);
	List<Receipt> findAllReceipts();
	void assignSeatToUser(UserData user);
	List<UserData> getUsersFromSection(String section);
	String deleteUserFromTrain(String userName);
	Receipt updateSeatForUser(String input);
	String findSectionFromSeat(int currentSeat);
}

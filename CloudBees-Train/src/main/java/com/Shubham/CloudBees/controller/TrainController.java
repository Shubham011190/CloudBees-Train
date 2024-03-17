package com.Shubham.CloudBees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Shubham.CloudBees.entity.Receipt;
import com.Shubham.CloudBees.entity.UserData;
import com.Shubham.CloudBees.service.TrainService;

@RestController
@RequestMapping("/cloudbees")
public class TrainController {
	
	@Autowired
	TrainService service;
	
	@PostMapping("/ticketPurchase")
	public Receipt purchaseTicket(@RequestBody UserData input) {
		return service.saveReceiptData(input);
	}
	
	@GetMapping("/receipts")
	public List<Receipt> getAllReceipts(){
		return service.findAllReceipts();
	}
	
	@GetMapping("/users/{section}")
	public List<UserData> getUsersBySection(@PathVariable String section){
		return service.getUsersFromSection(section);
	}
	
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestBody String userName) {
		service.deleteUserFromTrain(userName);
		return "User : " + userName + " removed from the Train!";
	}
	
	@PutMapping("/updateSeat")
	public Receipt updateSeat(@RequestBody String input) {
		return service.updateSeatForUser(input);
	}
}

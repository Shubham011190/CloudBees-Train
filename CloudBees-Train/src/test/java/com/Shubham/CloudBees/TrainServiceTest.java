package com.Shubham.CloudBees;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.Shubham.CloudBees.dao.DaoService;
import com.Shubham.CloudBees.entity.Receipt;
import com.Shubham.CloudBees.entity.Seat;
import com.Shubham.CloudBees.entity.UserData;
import com.Shubham.CloudBees.service.TrainService;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class TrainServiceTest {
	
	@InjectMocks
	private TrainService service;
	
	@Mock
	DaoService daoService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Before
	public void initData() {
		UserData input = new UserData("Vishal", "Singhal", "email");
		Seat seat = new Seat("A", 1);
		input.setSeat(seat);
		service.saveReceiptData(input);
	}
	
	@Test
	public void testPurchaseTicket() {
		UserData input = new UserData("Shubham", "Singhal", "email");
		Seat seat = new Seat("A", 2);
		input.setSeat(seat);
		Receipt output = new Receipt("London", "France", input, 20.0);
		Receipt expected = service.saveReceiptData(input);
		assertEquals(expected.toString(), output.toString());
	}
	
	@Test
	public void testFindAllReceipts() {
		List<Receipt> expected = new ArrayList<>();
		assertEquals(expected, service.findAllReceipts());
	}
	
	@Test
	public void testGetUsersFromSection() {
		assertEquals(new ArrayList<>(), service.getUsersFromSection("A"));
	}
	
	@Test
	public void testDeleteUserFromTrain() {
		UserData user = new UserData("Shubham", "Singhal", "email");
		Seat seat = new Seat("A", 2);
		user.setSeat(seat);
		when(daoService.getUserFromName("Shubham", "Singhal")).thenReturn(user);
		service.deleteUserFromTrain("Shubham Singhal");
	}
	
	@Test
	public void testUpdateSeatForUser() {
		System.out.println(service.updateSeatForUser("Shubham Singhal 4"));
	}
}

package com.Shubham.CloudBees.service;

import com.Shubham.CloudBees.entity.UserData;

public interface UserService{
	
	UserData getUserByFirstAndLastName(String firstName, String lastName);

}

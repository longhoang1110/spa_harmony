package com.spa.user;

import com.spa.entity.Customer;
import com.spa.entity.User;


public interface UserService {
	
	void encodePassword(User user);
	
	void registerUser(User user, Customer customer);
	
	void createUserId(User user);
		
}

package com.spa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spa.entity.User;
import com.spa.user.UserRepository;

public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findUserByUserName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("No customer found with the email " + username);
		}
		
		return new CustomerUserDetails(user);
	}
	
}

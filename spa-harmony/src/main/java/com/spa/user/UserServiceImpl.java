package com.spa.user;

import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spa.entity.Customer;
import com.spa.entity.Role;
import com.spa.entity.User;
import com.spa.role.RoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public void encodePassword(User user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		passwordEncoder.matches(encodePassword, encodePassword);
		user.setPassword(encodePassword);
	}
	
	@Override
	public void registerUser(User user, Customer customer) {
		
		createUserId(user);
		user.setCustomer(customer);
		user.setEmail(customer.getEmail());
		encodePassword(user);
		
		Role role = roleRepository.findRoleByRoleName(Role.CUSTOMER);
		
		user.setRoles(Set.of(role));
		
		userRepository.save(user);
	}
	
	@Override
	public void createUserId(User user) {
		 user.setId(UUID.randomUUID().toString());
	}
	
	

}

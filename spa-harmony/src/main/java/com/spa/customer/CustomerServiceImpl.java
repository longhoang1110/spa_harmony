package com.spa.customer;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.spa.entity.Customer;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void registerCustomer(Customer customer) {
		createCustomerId(customer);
		customerRepository.save(customer);
	}

	@Override
	public void createCustomerId(Customer customer) {
		customer.setId(UUID.randomUUID().toString());
	}
}

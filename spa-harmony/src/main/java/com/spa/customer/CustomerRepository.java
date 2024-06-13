package com.spa.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spa.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}

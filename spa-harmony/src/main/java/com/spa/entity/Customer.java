package com.spa.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "t01_customer")
public class Customer {
	
	@Id   
    @Column(name = "t01_customer_id")
    private String id;
	
	@Column(name = "t01_name", nullable = false)
	@NotNull(message = "Name cannot be null")
    private String name;
    
	@NotNull(message = "Email cannot be null")
    @Column(name = "t01_email", nullable = false, unique = true)
    private String email;
    
	@NotNull(message = "Phone cannot be null")
//	@Size(min = 10, max = 10, message = "Phone must be exactly 10 characters")
    @Column(name = "t01_phone", nullable = false, unique = true)
    private String phone;
    
    @Column(name = "t01_created_date")
    private Date createdDate;
    
    @Column(name = "t01_birth_date")
    private Date birthDate;
    
    @Column(name = "t01_enabled")
    private boolean enabled = true;
    
    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private User user;
    
    public Customer() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null && id.length() == 36) {
			this.id = id;
		} else {
			throw new IllegalArgumentException("Customer ID must be 36 characters long");
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}

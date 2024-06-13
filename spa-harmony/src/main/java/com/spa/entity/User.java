package com.spa.entity;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "t02_user")
public class User{
	
	@Id   
    @Column(name = "t02_user_id")
    private String id;
	
    @Column(name = "t02_username", nullable = false, unique = true)
    @NotNull(message = "Username cannot be null")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Column(name = "t02_password", nullable = false)
    @Pattern(regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*", message = "Password must contain at least one special character")
//    @Min(value = 6, message = "Password must greater than or equal to 6")
    private String password;
    
    @Transient
    private String confirmPassword;

    @Column(name = "t02_email", nullable = false, unique = true)
    private String email;

    @Column(name = "t02_enabled", nullable = false)
    private boolean enabled = true;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t02_customer_id", referencedColumnName = "t01_customer_id", unique = true)
    private Customer customer;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    			name = "t04_user_role",
    			joinColumns = @JoinColumn(name = "t04_user_id"),
    			inverseJoinColumns = @JoinColumn(name = "t04_role_id")
    		)
    private Set<Role> roles = new HashSet<>();
    
    public User() {
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		if (id != null && id.length() == 36) {
			this.id = id;
		} else {
			throw new IllegalArgumentException("User ID must be 36 characters long");
		}
	}
	
	public boolean hasRole(String roleName) {
		Iterator<Role> iterator = roles.iterator();
		
		while (iterator.hasNext()) {
			Role role = iterator.next();
			if (role.getName().equals(roleName)) {
				return true;
			}
		}
		
		return false;
	}



    
	
	
	
    
}

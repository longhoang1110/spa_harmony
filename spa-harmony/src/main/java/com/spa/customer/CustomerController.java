package com.spa.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spa.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	@GetMapping("customer/index_customer")
	public String viewHomePageCustomer( Model model, HttpSession session) {
		
		User user =(User) session.getAttribute("user");
		
		if (user == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("user", user);

		return "customer/index_customer";
	}
}

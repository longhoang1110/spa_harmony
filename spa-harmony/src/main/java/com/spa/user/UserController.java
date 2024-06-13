package com.spa.user;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spa.customer.CustomerService;
import com.spa.entity.Customer;
import com.spa.entity.User;

import jakarta.validation.Valid;

@Controller
public class UserController {

	private final UserService userService;
	private final CustomerService customerService;

	public UserController(UserService userService, CustomerService customerService) {
		this.userService = userService;
		this.customerService = customerService;
	}
	
	@InitBinder
	public void initBider(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("customer", new Customer());
		return "register/register";
	}
	
	@PostMapping("/register")
    public String submitRegistrationForm(@Valid @ModelAttribute("user") User user,
    									BindingResult userBindingResult,
    									@Valid @ModelAttribute("customer") Customer customer, 
    									BindingResult customerBindingResult, 
    									Model model,
    									RedirectAttributes redirectAttributes) {
        
        if (userBindingResult.hasErrors() || customerBindingResult.hasErrors()) {      
			return "register/register";
		}
        
        try {
        	 customerService.registerCustomer(customer);
             userService.registerUser(user, customer);
		} catch (Exception e) {
			return "500";
		}
             
        redirectAttributes.addFlashAttribute("successMessage", "Register successfull");

        return "redirect:/login";
    }

	@GetMapping("/login")
	public String loginUser(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

}

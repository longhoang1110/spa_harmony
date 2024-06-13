package com.spa.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/403")
	public String viewAdPage() {
		return "403";
	}
	
	@GetMapping("/forgot_password")
	public String viewFindPassword() {
		return "password/forgot_password";
	}
		
}

package com.spa.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin/index")
	public String adminIndex() {
		return "/admin/index";
	}
	
}

package com.spa.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spa.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class DatabaseLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
//	private final UserService userService;
//	
//	public DatabaseLoginSuccessHandler(UserService userService) {
//		this.userService = userService;
//	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		
		
		HttpSession session = request.getSession();
		session.setAttribute("user",user);
		
		String redirectUrl = request.getContextPath() + "/customer/index_customer";
        response.sendRedirect(redirectUrl);
		
//		super.onAuthenticationSuccess(request, response, authentication);
	}

}

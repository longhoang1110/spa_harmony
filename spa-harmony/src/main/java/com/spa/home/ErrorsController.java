package com.spa.home;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorsController implements ErrorController {
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            
            if (statusCode == 403) {
                return "403";  // Trả về trang lỗi 403
            } else if (statusCode == 404) {
                return "404";  // Trả về trang lỗi 404
            } else if (statusCode == 500) {
                return "500";  // Trả về trang lỗi 500
            }
        }
        return "error";  
    }

    public String getErrorPath() {
        return "/error";
    }
	
}

package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.interfaces.IAdmin;



@Controller
@RequestMapping("/admin")
public class AdminController {
	
	//@Autowired IAdmin adminService;
	@GetMapping("/profile")
	
	public String viewProfile() {
		return "admin/admin-profile";
	}
	
}
	
package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.service.interfaces.IStudent;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired IStudent studentService;
	
	@GetMapping("/profile")
	public String viewProfile() {
		return "Profile";
	}

}

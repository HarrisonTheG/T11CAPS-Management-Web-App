package sg.edu.iss.caps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.service.interfaces.IStudent;

//changes by zhangtian
//changes by hafiz
//changes by wanling
//changes by zavier
//changes by hafiz again
@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired IStudent studentService;
	
	@GetMapping("/profile")
	public String viewProfile(HttpSession session, Model model) {
		model.addAttribute("user", session.getAttribute("user"));
		
		return "Profile";
	}

}

package sg.edu.iss.caps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.User;
//import sg.edu.iss.caps.service.interfaces.IAdmin;
import sg.edu.iss.caps.utility.UtilityManager;



@Controller
@RequestMapping("/admin")
public class AdminController {
	
//	@Autowired IAdmin adminService;
	@GetMapping("/profile")
	public String viewProfile(HttpSession session, Model model) {
		
		User user = (User) session.getAttribute("user");
		long start = user.getEnrollmentDate();
		model.addAttribute("enrollDate", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(start)));
		return "Profile";
	}
	
	
}
	
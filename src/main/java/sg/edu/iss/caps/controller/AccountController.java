package sg.edu.iss.caps.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import sg.edu.iss.caps.model.MailVo;
import sg.edu.iss.caps.model.RoleType;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.interfaces.IUser;
import sg.edu.iss.caps.utility.UtilityManager;

@Controller
@RequestMapping("/account")
public class AccountController {

//	@Autowired private IStudent studentService;
//	@Autowired private ILecturer lecturerService;
//	@Autowired private IAdmin adminService;
	@Autowired private IUser userService;
	
	@Autowired
	public void setAccount(IUser uService) {
		this.userService = uService;
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "LoginPage";
	}

	@GetMapping("sendMail")
	public String sendMail(){
		MailVo mail=new MailVo("PCXGudrew@163.com","979024642@qq.com","hello","hello");
		userService.sendEmailNotification(mail);
		return "LoginPage";
	}

	@PostMapping("/authenticate")
	public String authenticate(@Valid String email, String password, String identity, HttpSession session, Model model) {

		User user = userService.findUser(email, password, identity);

		if(user != null) {
			session.setAttribute("user", user);
			//session.getAttribute("user");
			//model.addAttribute(user);
				long start = user.getEnrollmentDate();
				//System.out.println(UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(start)));
				model.addAttribute("enrollDate", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(start)));
			return "Profile";
//			if(user.getRole() == RoleType.STUDENT)
//				return "student/student-profile";
//			else if(user.getRole() == RoleType.LECTURER)
//				return "lecturer/lecturer-profile";
//			else
//				return "admin/admin-profile";
		}
		return "LoginPage";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//remove session user after logs out
		session.setAttribute("user", null);
		return "LoginPage";
	}
	
	@GetMapping("/others/{id}")
	public String viewOtherProfile(HttpSession session, @PathVariable int id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		
		long start = user.getEnrollmentDate();
		model.addAttribute("enrollDate", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(start)));
		
		return "OthersProfile";
	}
	
}

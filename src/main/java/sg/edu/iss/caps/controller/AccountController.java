package sg.edu.iss.caps.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import sg.edu.iss.caps.model.LoginUser;
import sg.edu.iss.caps.model.MailVo;
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
	public String login(Model model,HttpSession session) {
		User checklogin=(User) session.getAttribute("user");
		if(checklogin!=null) {
			model.addAttribute("enrollDate", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(checklogin.getEnrollmentDate())));
			return "Profile";
		}

	
		if (model.getAttribute("loginUser") == null) {
		model.addAttribute("loginUser", new LoginUser());
		}
		
	
		
		return "LoginPage";
	}
	
	
	//@Valid String email, BindingResult br, String password, String identity, HttpSession session, Model model
	@PostMapping("/authenticate")
	public String authenticate(@Valid LoginUser user, BindingResult br, String identity, HttpSession session, Model model) {
		System.out.println(123);
		System.out.println(br.hasErrors());
		if(br.hasErrors()) {
			//model.addAttribute("user", new LoginUser());
			return "LoginPage";
		}
		
		User user1 = userService.findUser(user.getEmail(), user.getPassword(), identity);
		System.out.println(1);
		
		if(user1 != null) {
			session.setAttribute("user", user1);
			//session.getAttribute("user");
			//model.addAttribute(user);
				long start = user1.getEnrollmentDate();
				//System.out.println(UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(start)));
				model.addAttribute("enrollDate", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(start)));
			return "Profile";
		}
		System.out.println(2);
		model.addAttribute("errorMsg", "Information above is incorrect!");
		return "LoginPage";
	}
	
	@GetMapping("/logout")
	public RedirectView logout(HttpSession session) {
		//remove session user after logs out
		session.setAttribute("user", null);
		
		return new RedirectView("/account/login");
	}
	
	@GetMapping("/others/{id}")
	public String viewOtherProfile(HttpSession session, @PathVariable int id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		
		long start = user.getEnrollmentDate();
		model.addAttribute("enrollDate", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(start)));
		
		return "OthersProfile";
	}
	
	//Test email notification
	@GetMapping("sendMail")
	public String sendMail(){
		MailVo mail=new MailVo("PCXGudrew@163.com","979024642@qq.com","hello","hello");
		userService.sendEmailNotification(mail);
		return "LoginPage";
	}
	
}

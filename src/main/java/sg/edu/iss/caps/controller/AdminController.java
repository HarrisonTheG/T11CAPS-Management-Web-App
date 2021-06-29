package sg.edu.iss.caps.controller;

import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.DTO.manageCourse.EditUserDto;
import sg.edu.iss.caps.model.User;
//import sg.edu.iss.caps.service.interfaces.IAdmin;
import sg.edu.iss.caps.utility.UtilityManager;
import sg.edu.iss.caps.service.interfaces.IUser;



@Controller
@RequestMapping("/admin")
public class AdminController {

//	@Autowired IAdmin adminService;
	@Autowired IUser userService;

	@GetMapping("/profile")
	public String viewProfile(HttpSession session, Model model) {

		User user = (User) session.getAttribute("user");
		long start = user.getEnrollmentDate();
		model.addAttribute("enrollDate", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(start)));
		return "Profile";
	}

	//Add new user
		@RequestMapping("/add")
		public String addUser(Model model, HttpSession session) {
			session.getAttribute("user");
			return "admin/addUserForm";
		}

		@PostMapping("/saveNewUser")
		public String saveNewUser(@ModelAttribute("user") @Valid EditUserDto addUserDto, BindingResult bindingResult, Model model) throws ParseException {
			userService.AddUser(addUserDto);
			return "admin/addUserSuccess";
		}


}

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.DTO.manageCourse.EditUserDto;
import sg.edu.iss.caps.model.Course;
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
		//this is if the details are edited by admin
		if(user!=null) {
			user=userService.findUserById(user.getId());
			session.setAttribute("user", user);
		}
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

		//get delete student by admin
		@GetMapping("/delete/{id}")
		public String deleteStudent(@PathVariable("id")int id,Model model,HttpSession session) {
			session.getAttribute("user");
			User selecteduser=userService.findUserById(id);
			model.addAttribute("user",selecteduser);
			model.addAttribute("enrolment",UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selecteduser.getEnrollmentDate())));
			return "admin/deletestudent";
		}
		
	//post delete student by admin
		@PostMapping("/deleteStudent")
		public String editStudent(@ModelAttribute("user") @Valid User user,BindingResult bindingResult,Model model) throws ParseException{
			userService.adminDeleteStudent(user);
			model.addAttribute("listStudents", userService.listStudents(""));
			return "admin/viewStudentList";
		}
		
		//view student list
		@RequestMapping("/student-list")
		public String viewStudentList(Model model, @Param("keyword") String keyword) {
			List<User> listStudents = userService.listStudents(keyword);
	        model.addAttribute("listStudents", listStudents);
	        model.addAttribute("keyword", keyword);
			return "admin/viewStudentList";
		}
	//Edit User
	@GetMapping("/edit/{id}")
	public String EditUserDetails(@PathVariable("id") int id, Model model, HttpSession session) {
		session.getAttribute("user");
		User selectedUser=userService.findUserById(id);

		model.addAttribute("user",selectedUser);
		model.addAttribute("enrolmentDate",UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selectedUser.getEnrollmentDate())));
		return "admin/editUser";
	}

	@PostMapping("/save")
	public String saveUserForm(@ModelAttribute("user") @Valid EditUserDto editUserDto, BindingResult bindingResult, Model model, HttpSession session) throws ParseException {

		userService.editUser(editUserDto);
		model.addAttribute("user",editUserDto);
		return"admin/editUserSuccess";
	}

}

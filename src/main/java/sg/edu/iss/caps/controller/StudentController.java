package sg.edu.iss.caps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import sg.edu.iss.caps.model.MailVo;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.interfaces.ICourse;
import sg.edu.iss.caps.service.interfaces.IStudent;
import sg.edu.iss.caps.service.interfaces.IStudentCourse;
import sg.edu.iss.caps.service.interfaces.IUser;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired IStudent studentService;
	@Autowired IUser userService;
	@Autowired ICourse courseService;
	@Autowired IStudentCourse scService;

	@GetMapping("/profile")
	public String viewProfile(HttpSession session, Model model) {
		model.addAttribute("user", session.getAttribute("user"));

		return "Profile";
	}
	
	@GetMapping("/enroll") @ResponseBody
	public RedirectView enrollCourse(HttpSession session, Model model, @RequestParam("studentId") int sId, @RequestParam("courseId") int cId, 
			@RequestParam("msgHeader") String header, @RequestParam("msgBody") String body) {
		
		studentService.enrollStudentToCourse(sId, cId);
		
		User student = (User) session.getAttribute("user");
		MailVo mail=new MailVo("PCXGudrew@163.com", student.getEmail(), header, body);
		userService.sendEmailNotification(mail);
		
		return new RedirectView ("/course/viewCourses/" + sId);
	}
	
	
	
}

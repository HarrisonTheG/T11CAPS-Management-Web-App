package sg.edu.iss.caps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.interfaces.ILecturer;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {
	
	@Autowired ILecturer lecturerService;
	
	@GetMapping("/profile")
	public String viewProfile() {
		return "Profile";
	}
	
	@GetMapping("/courses")
	public String viewCoursesTaught() {
		return "lecturer/courses";
	}
	
	@GetMapping("/course-detail")
	public String viewCourseDetails() {
		return "lecturer/course-detail";
	}
	
	@GetMapping("/student-list")
	public String viewCourseStudentList(Model model, @Param("keyword") String keyword) {
		List<User> listUsers = lecturerService.listAll(keyword);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);
		return "lecturer/student-list";
	}
	
	@GetMapping("/grade-student")
	public String gradeStudent() {
		return "lecturer/grade-student";
	}
	
	 
	
	
	
	

}

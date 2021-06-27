package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.service.interfaces.ICourse;


//change from chenxiao
@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired ICourse courseService;
	
	@GetMapping("/viewCourses")
	public String viewAllCourses(Model model) {
		
		model.addAttribute("courses", courseService.listAllCourses());
		return "Courses";
	}
	
	@GetMapping("/details")
	public String viewCourseDetails() {
		return "CourseDetail";
	}
	
	@GetMapping("/studentCourses")
	public String viewSpecificStudentAllCourses() {
		return "student/student-courses";
	}
	
	

}


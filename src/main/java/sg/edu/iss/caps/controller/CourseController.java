package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.service.interfaces.ICourse;


//change from chenxiao
@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired ICourse courseService;
	
	@GetMapping("/viewCourses")
	public String viewAllCourses() {
		return "ListTableView";
	}
	
	@GetMapping("/studentCourses")
	public String viewSpecificStudentCourses() {
		return "student/student-courses";
	}

}


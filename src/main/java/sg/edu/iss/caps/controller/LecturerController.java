package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.service.interfaces.ILecturer;


@Controller
@RequestMapping("/lecturer")
public class LecturerController {
	
	@Autowired ILecturer lecturerService;
	
	@GetMapping("/profile")
	public String viewProfile() {
		return "lecturer/lecturer-profile";
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
	public String viewCourseStudentList() {
		return "lecturer/student-list";
	}
	
	@GetMapping("/grade-student")
	public String gradeStudent() {
		return "lecturer/grade-student";
	}
	
	
	
	

}

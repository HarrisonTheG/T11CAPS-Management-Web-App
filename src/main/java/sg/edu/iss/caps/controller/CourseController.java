package sg.edu.iss.caps.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.service.interfaces.ICourse;

//change from chenxiao
@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired ICourse courseService;
	
	@GetMapping("/viewCourses")
	public String viewAllCourses(Model model, HttpSession session) {
		session.getAttribute("user");
		model.addAttribute("courses", courseService.listAllCourses());
		return "Courses";
	}
	
	
	@GetMapping("/details/{id}")
	public String viewCourseDetails(@PathVariable("id") int id, Model model, HttpSession session) {
		System.out.println(id);
		session.getAttribute("user");
		Course selectedCourse = courseService.findCourse(id).orElse(null);
		System.out.println(courseService.findCourse(id).orElse(null));
		
		model.addAttribute("course", selectedCourse);
		model.addAttribute("lecturers", selectedCourse.getUser().stream().filter(x -> x.getRole() == RoleType.LECTURER).collect(Collectors.toList()));
		model.addAttribute("students", selectedCourse.getStudentCourses().stream().map(x -> x.getUser()).collect(Collectors.toList()));
		
		return "CourseDetail";
	}
	
	@GetMapping("/studentCourses")
	public String viewSpecificStudentAllCourses() {
		return "student/student-courses";
	}
	
	

}


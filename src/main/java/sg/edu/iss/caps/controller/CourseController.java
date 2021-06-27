package sg.edu.iss.caps.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.service.interfaces.ICourse;
import sg.edu.iss.caps.service.interfaces.IStudent;
import sg.edu.iss.caps.service.interfaces.IStudentCourse;
import sg.edu.iss.caps.service.interfaces.IUser;


@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired ICourse courseService;
	@Autowired IUser userService;
	@Autowired IStudentCourse scService;
	
	@GetMapping("/viewCourses")
	public String viewProfile() {
		return "ListTableView";
	}

	@GetMapping(value = "/{cid}/addStudentToCourse/{sid}")
	public String addStudentToCourse(@PathVariable Map<Integer, Integer> pathVarsMap) {
		Integer studentId = pathVarsMap.get("sid");
		Integer courseId = pathVarsMap.get("cid");
		scService.addStudentToCourse(courseService.findCourseById(courseId), userService.findStudentById(studentId));
		return "forward:/{cid}";
	}
	
	@GetMapping(value = "/{cid}/deleteStudentFromCourse/{sid}")
	public String deleteStudentFromCourse(@PathVariable Map<Integer, Integer> pathVarsMap) {
		Integer studentId = pathVarsMap.get("sid");
		Integer courseId = pathVarsMap.get("cid");
		scService.removeStudentFromCourse(courseService.findCourseById(courseId), userService.findStudentById(studentId));
		return "forward:/{cid}";
	}
}


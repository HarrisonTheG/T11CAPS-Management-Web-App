package sg.edu.iss.caps.controller;


import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.User;

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
	
	public String viewProfile(Model model, @Param("keyword") String keyword) {
		List<Course> listCourses = courseService.listAll(keyword);
        model.addAttribute("listCourses", listCourses);
        model.addAttribute("keyword", keyword);
		return "ListTableView";
	}
	
	

	@GetMapping("/{cid}")
	public String viewCourseDetails(Model model, @PathVariable("cid") int cid) {
		Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
		return "admin/course-detail";
	}

	@GetMapping(value = "/{cid}/addStudentToCourse/{sid}")
	public String addStudentToCourse(@PathVariable("cid") int cid, @PathVariable("sid") int sid) {
		scService.addStudentToCourse(courseService.findCourseById(cid), userService.findStudentById(sid));
		return "forward:/course/"+cid+"/student-list";
	}

	@GetMapping(value = "/{cid}/deleteStudentFromCourse/{sid}")
	public String deleteStudentFromCourse(@PathVariable("cid") int cid, @PathVariable("sid") int sid) {
		scService.removeStudentFromCourse(courseService.findCourseById(cid), userService.findStudentById(sid));
		return "forward:/course/"+cid+"/student-list";
	}

	@GetMapping("/{cid}/student-list")
	public String viewCourseStudentList(Model model, @PathVariable("cid") int cid, @Param("keyword") String keyword) {
		List<User> listUsers = userService.listStudents(keyword);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);
        Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
        List<User> listStudentsInCourse = scService.listStudentsInCourse(course);
        model.addAttribute("students", listStudentsInCourse);
		return "admin/student-list";
	}
}

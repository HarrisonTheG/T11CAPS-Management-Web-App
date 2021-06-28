package sg.edu.iss.caps.controller;

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
	public String viewAllCourses() {
		return "ListTableView";
	}

	@GetMapping("/studentCourses")
	public String viewSpecificStudentCourses() {
		return "student/student-courses";
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

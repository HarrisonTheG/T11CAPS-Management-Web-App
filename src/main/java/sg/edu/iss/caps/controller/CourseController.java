package sg.edu.iss.caps.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

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
	public String viewSpecificStudentAllCourses(HttpSession session, Model model) {
		return "student/student-courses";
	}

	@GetMapping("/search")
	public String searchCourse(HttpSession session, Model model, @Param("keyword") String keyword) {
		List<Course> searchedCourses = courseService.listAll(keyword);
        model.addAttribute("courses", searchedCourses);
        model.addAttribute("keyword", keyword);
        return "Courses";
	}

	//Get list of students in course
	@GetMapping("/{cid}/student-list")
	public String viewCourseStudentList(Model model, @Param("keyword") String keyword, @PathVariable("cid") int cid, HttpSession session) {
		session.getAttribute("user");
		Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
        List<User> listUsers = scService.listStudentsInCourse(course);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);
		return "lecturer/student-list";
	}

	//Manage students
	@GetMapping(value = "/{cid}/addStudentToCourse/{sid}")
	public String addStudentToCourse(@PathVariable("cid") int cid, @PathVariable("sid") int sid, HttpSession session) {
		session.getAttribute("user");
		scService.addStudentToCourse(courseService.findCourseById(cid), userService.findStudentById(sid));
		return "forward:/course/"+cid+"/student-list";
	}

	@GetMapping(value = "/{cid}/deleteStudentFromCourse/{sid}")
	public String deleteStudentFromCourse(@PathVariable("cid") int cid, @PathVariable("sid") int sid, HttpSession session) {
		session.getAttribute("user");
		scService.removeStudentFromCourse(courseService.findCourseById(cid), userService.findStudentById(sid));
		return "forward:/course/"+cid+"/student-list";
	}

	@GetMapping("/{cid}/edit-student-list")
	public String viewCourseStudentList(Model model, @PathVariable("cid") int cid, @Param("keyword") String keyword, HttpSession session) {
		session.getAttribute("user");
		List<User> listUsers = userService.listStudents(keyword);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);
        Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
        List<User> listStudentsInCourse = scService.listStudentsInCourse(course);
        model.addAttribute("students", listStudentsInCourse);
		return "admin/course-student-list";
	}
	
	// Manage lecturers
	@GetMapping(value = "/{cid}/addLecturerToCourse/{uid}")
	public String addLecturerToCourse(@PathVariable("cid") int cid, @PathVariable("uid") int uid, HttpSession session) {
		session.getAttribute("user");
		List<User> lecturer = new ArrayList<User>();
		lecturer.add(userService.findLecturerById(uid));
		courseService.addLecturerToCourse(lecturer, cid);
		return "forward:/course/"+cid+"/lecturer-list";
	}

	@GetMapping(value = "/{cid}/deleteLecturerFromCourse/{uid}")
	public String deleteLecturerFromCourse(@PathVariable("cid") int cid, @PathVariable("uid") int uid, HttpSession session) {
		session.getAttribute("user");
		courseService.deleteLecturerFromCourse(userService.findLecturerById(uid), cid);
		return "forward:/course/"+cid+"/lecturer-list";
	}
	
	@GetMapping("/{cid}/edit-lecturer-list")
	public String viewCourseLecturerList(Model model, @PathVariable("cid") int cid, @Param("keyword") String keyword, HttpSession session) {
		session.getAttribute("user");
		//get full list of lecturers
		List<User> listUsers = userService.listLecturers(keyword);
        model.addAttribute("listUsers", listUsers);
        //add search keyword to model
        model.addAttribute("keyword", keyword);
        //add course id to model
        Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
        //add lecturers teaching the course to model
        model.addAttribute("lecturers", course.getUser().stream().filter(x -> x.getRole() == RoleType.LECTURER).collect(Collectors.toList()));
		return "admin/course-lecturer-list";
	}
}

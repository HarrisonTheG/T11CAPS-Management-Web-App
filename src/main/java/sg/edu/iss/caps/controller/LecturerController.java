package sg.edu.iss.caps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.interfaces.ICourse;
import sg.edu.iss.caps.service.interfaces.ILecturer;
import sg.edu.iss.caps.service.interfaces.IStudentCourse;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {
	
	@Autowired ILecturer lecturerService;
	@Autowired ICourse courseService;
	@Autowired IStudentCourse scService;
	
	@GetMapping("/profile")
	public String viewProfile() {
		return "Profile";
	}
	
	//View courses taught by lecturer
	@GetMapping("/courses/{id}")
	public String viewCoursesTaught(Model model, HttpSession session, User user, @PathVariable("id") Integer id) {
		System.out.println(id);
		session.getAttribute("user");
		model.addAttribute("courses", courseService.findCourseByLecturerId(id));
		model.addAttribute("id", id);
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
	
	
	@GetMapping("/{cid}/grade-student")
	public String gradeStudent(Model model, HttpSession session,@PathVariable("cid") int cid) {
		session.getAttribute("user");
		Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
		List<Student_Course> students = scService.listStudentsGradesInCourse(course);
        model.addAttribute("students", students);
		return "lecturer/grade-student";
	}
	
	 
	
	
	
	

}

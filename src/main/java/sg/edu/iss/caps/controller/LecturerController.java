package sg.edu.iss.caps.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.DTO.manageCourse.EditCourseDto;
import sg.edu.iss.DTO.manageCourse.EditUserDto;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.interfaces.ICourse;
import sg.edu.iss.caps.service.interfaces.ILecturer;
import sg.edu.iss.caps.service.interfaces.IStudentCourse;
import sg.edu.iss.caps.service.interfaces.IUser;
import sg.edu.iss.caps.utility.UtilityManager;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	@Autowired ILecturer lecturerService;
	@Autowired ICourse courseService;
	@Autowired IStudentCourse scService;
	@Autowired IUser userService;

	@GetMapping("/profile")
	public String viewProfile(HttpSession session, Model model) {
		User user=(User)session.getAttribute("user");
		//Redirect to Error Page if not lecturer
		if(user.getRole()!=RoleType.LECTURER)
			return "Error";	
		long start = user.getEnrollmentDate();
		model.addAttribute("enrollDate", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(start)));
		return "Profile";
	}
	
	//View courses taught by lecturer
	@GetMapping("/courses/{id}")
	public String viewCoursesTaught(Model model, HttpSession session, User user, @PathVariable("id") Integer id) {
		User uservalidate=(User)session.getAttribute("user");
		//Redirect to Error Page if not lecturer, or lecturer try to view other lecturers
		if(uservalidate.getRole()!=RoleType.LECTURER||uservalidate.getRole()==RoleType.LECTURER && uservalidate.getId()!=id)
			return "Error";	
		
		model.addAttribute("courses", courseService.findCourseByLecturerId(id));
		model.addAttribute("id", id);
		return "lecturer/courses";
	}

	
	//lecturer View entire student list
	@GetMapping("/student-list")
	public String viewStudentList(Model model, @Param("keyword") String keyword,HttpSession session) {
		User user=(User)session.getAttribute("user");
		//Redirect to Error Page if not lecturer
		if(user.getRole()!=RoleType.LECTURER)
			return "Error";	
		
		List<User> listStudents = userService.listStudents(keyword);
        model.addAttribute("listStudents", listStudents);
        model.addAttribute("keyword", keyword);
		return "lecturer/student-list";
	}
	
	//View student list in specific course
	@GetMapping("/{cid}/student-list")
	public String viewCourseStudentList(HttpSession session, Model model,@PathVariable("cid") int cid, @Param("keyword") String keyword) {
		User user=(User)session.getAttribute("user");
		//Redirect to Error Page if not lecturer
		if(user.getRole()!=RoleType.LECTURER)
			return "Error";
		
		Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
		List<User> listStudentsCourse = scService.listStudentsInCourse(course, keyword);
        model.addAttribute("listStudents", listStudentsCourse);
        model.addAttribute("keyword", keyword);
		return "lecturer/student-list";
	}

	//Get list of students to grade for a course- RESTRICT students and admin
	@GetMapping("/{id}/grade-student-list")
	public String gradeStudentList(Model model, HttpSession session, @PathVariable("id") int id) {
		User user=(User)session.getAttribute("user");
		//Redirect to Error Page if not lecturer
		if(user.getRole()!=RoleType.LECTURER)
			return "Error";
		
		Course course = courseService.findCourseById(id);
        model.addAttribute("course", course);
		List<Student_Course> students = scService.listStudentsGradesInCourse(course);
        model.addAttribute("students", students);
		return "lecturer/grade-student-list";
	}
		
	//Edit grade of student- only lecturers can grade
	@GetMapping("/{cid}/grade-student-list/edit/{id}")
	public String editStudentGrade(@PathVariable("id") int id,@PathVariable("cid") int cid,Model model, HttpSession session) {
		User user=(User)session.getAttribute("user");
		//Redirect to Error Page if not lecturer
		if(user.getRole()!=RoleType.LECTURER)
			return "Error";
		
		Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
		session.getAttribute("user");
		Student_Course selectedStudentCourse = scService.findStudentCourseById(id);
		model.addAttribute("selectedStudentCourse",selectedStudentCourse);

		return "lecturer/edit";
	}

	//To save grade after editing
	@PostMapping("{cid}/grade-student-list")
	public String saveGradeForm(@ModelAttribute("selectedStudentCourse") @Valid Student_Course selectedStudentCourse,BindingResult bindingResult,Model model,@PathVariable("cid") int cid,HttpSession session) {
		User user=(User)session.getAttribute("user");
		//Redirect to Error Page if not lecturer
		if(user.getRole()!=RoleType.LECTURER)
			return "Error";
		
		System.out.println(selectedStudentCourse.getGrade());
		scService.editStudentsGradesInCourse(selectedStudentCourse);
		model.addAttribute("selectedStudentCourse",selectedStudentCourse);
		Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
		return "redirect:/lecturer/"+cid+"/grade-student-list";
	}
	
	//View student performance and profile from student list
	@GetMapping("/studentCourses/{id}")
	public String viewStudentPerformanceForLecturer(HttpSession session, Model model, @PathVariable("id") int id) {
		session.getAttribute("user");
		System.out.println(userService.findStudentById(id));
		User student = userService.findUserById(id);
		model.addAttribute("student", student);
		model.addAttribute("listStudentCourses", scService.findStudentCoursesByStudentId(id));
		model.addAttribute("cgpa", UtilityManager.GradesToGPA(scService.findStudentCoursesByStudentId(id)));
		return "lecturer/student-list";
	}
	
	//View all lecturers-only viewed by admin
	@GetMapping("/viewLecturers")
	public String viewAllLecturers(Model model, HttpSession session, @Param("keyword") String keyword) {
		User user=(User)session.getAttribute("user");
		//Redirect to Error Page if not ADMIN
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";
		model.addAttribute("lecturers", userService.listLecturers(keyword));
		model.addAttribute("keyword", keyword);
		return "lecturer/lecturer-list";
	}

	//admin delete lecturer
	@GetMapping("/delete/{id}")
	public String deleteLecturer(@PathVariable("id") int id, Model model, HttpSession session) {
		User user=(User)session.getAttribute("user");
		//Redirect to Error Page if not ADMIN
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";
		User selectedlecturer = userService.findLecturerById(id);
		model.addAttribute("lecturer",selectedlecturer);
		return "admin/deleteLecturer";
	}

	//Delete lecturer
	@Transactional
	@PostMapping("/delete")
	public String delete(@ModelAttribute("lecturer") User lecturer, Model model, HttpSession session) {
		User lecturerToDelete = userService.findLecturerById(lecturer.getId());
		//Find courses that lecturer teaches
		List<Course> coursesTaught = lecturerToDelete.getCourse();
		//Delete lecturer from those courses
		for (Course course: coursesTaught) {
			List<User> courseLecturers = course.getUser();
			courseLecturers.remove(lecturerToDelete);
			course.setUser(courseLecturers);
		}
		//Delete lecturer from Users table
		userService.delete(lecturerToDelete);
		return "redirect:/lecturer/viewLecturers";
	}
	
}

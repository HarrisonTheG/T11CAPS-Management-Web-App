package sg.edu.iss.caps.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.DTO.manageCourse.EditCourseDto;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.MailVo;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.service.interfaces.ICourse;
import sg.edu.iss.caps.service.interfaces.IStudentCourse;
import sg.edu.iss.caps.service.interfaces.IUser;
import sg.edu.iss.caps.utility.UtilityManager;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired ICourse courseService;
	@Autowired IUser userService;
	@Autowired IStudentCourse scService;
	
	//View all available courses
	@GetMapping("/viewCourses")
	public String viewAllCourses(Model model, HttpSession session) {
		
		//Redirect to Error Page if not authorised
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";
		
		model.addAttribute("courses", courseService.listAllCourses());
		return "Courses";
	}

	//View courses specific to one student
	@GetMapping("/viewCourses/{id}")
	public String viewAllUnenrolledCourses(@PathVariable("id") int id, Model model, HttpSession session) {
		
		//Redirect to Error Page if not particular student
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.STUDENT||user.getId()!=id)
			return "Error";

		model.addAttribute("courses", courseService.findNewCoursesForStudents(id));

		return "Courses";
	}

	//Show the Course Details Page- roletype can view their respective screens
	@GetMapping("/details/{id}")
	public String viewCourseDetails(@PathVariable("id") int id, Model model, HttpSession session) {
		//System.out.println(id);
		session.getAttribute("user");
		Course selectedCourse = courseService.findCourse(id).orElse(null);
	

		model.addAttribute("course", selectedCourse);
		model.addAttribute("lecturers", selectedCourse.getUser().stream().filter(x -> x.getRole() == RoleType.LECTURER).collect(Collectors.toList()));
		model.addAttribute("students", selectedCourse.getStudentCourses().stream().map(x -> x.getUser()).collect(Collectors.toList()));
		
		model.addAttribute("startDateString", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selectedCourse.getStartDate())));
		model.addAttribute("endDateString", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selectedCourse.getEndDate())));
		model.addAttribute("duration", UtilityManager.CalculateCourseDuration(selectedCourse.getStartDate(), selectedCourse.getEndDate()));

		return "CourseDetail";
	}

	//Shows Student if student is enrolled in the course
	@GetMapping("/enrolledDetails/{id}")
	public String viewEnrolledCourseDetails(@PathVariable("id") int id, Model model, HttpSession session) {
				
		//Redirect to Error Page if not student
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.STUDENT)
			return "Error";
		
		Course selectedCourse = courseService.findCourse(id).orElse(null);

		model.addAttribute("course", selectedCourse);
		model.addAttribute("lecturers", selectedCourse.getUser().stream().filter(x -> x.getRole() == RoleType.LECTURER).collect(Collectors.toList()));
		model.addAttribute("students", selectedCourse.getStudentCourses().stream().map(x -> x.getUser()).collect(Collectors.toList()));
		
		//display the start date and duration in course details page
		model.addAttribute("startDateString", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selectedCourse.getStartDate())));
		model.addAttribute("endDateString", UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selectedCourse.getEndDate())));
		model.addAttribute("duration", UtilityManager.CalculateCourseDuration(selectedCourse.getStartDate(), selectedCourse.getEndDate()));

		return "student/enrolled-courseDetail";
	}

	//Add Course by admin
	@GetMapping("/add")
	public String AddCourse(Model model,HttpSession session) {
		//Redirect to Error Page if not admin
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";
		
		model.addAttribute("course",new EditCourseDto());
		return "admin/addCourse";
	}
	
	@PostMapping("/add")
	public String AddCourse(@ModelAttribute("course") @Valid EditCourseDto addCourseDto,BindingResult bindingResult,Model model) throws ParseException {
		if(bindingResult.hasErrors()){
			return "admin/addCourse";
		}
		
		courseService.AddCourse(addCourseDto);
		return"admin/AddSuccess";
	}
	
	
	//Edit Course by admin
	@GetMapping("/edit/{id}")
	public String EditCourseDetails(@PathVariable("id") int id,Model model, HttpSession session) {
		//Redirect to Error Page if not admin
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";
		
		Course selectedCourse=courseService.findCourse(id).orElse(null);

		model.addAttribute("course",selectedCourse);
		model.addAttribute("startdate",UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selectedCourse.getStartDate())));
		model.addAttribute("enddate",UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selectedCourse.getEndDate())));
		return "admin/editcourse";
	}

	@PostMapping("/edit")
	public String editCourse(@ModelAttribute("course") @Valid EditCourseDto editCourseDto,BindingResult bindingResult,Model model) throws ParseException {
		courseService.edit(editCourseDto);
		return"admin/editSuccess";
	}

	//delete course by admin
	@GetMapping("/delete/{id}")
	public String DeleteCourse(@PathVariable("id")int id,Model model,HttpSession session) {
		//Redirect to Error Page if not admin
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";
		
		Course selectedCourse=courseService.findCourse(id).orElse(null);
		model.addAttribute("course",selectedCourse);
		model.addAttribute("startdate",UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selectedCourse.getStartDate())));
		model.addAttribute("enddate",UtilityManager.ChangeDateTimeToString(UtilityManager.UnixToDate(selectedCourse.getEndDate())));
		return "admin/deletecourse";
	}

	@Transactional
	@PostMapping("/delete")
	public String deleteCourse(@ModelAttribute("course") Course course,Model model, HttpSession session) {
		Course todelete=courseService.findCourseById(course.getId());
		courseService.deleteCourse(todelete);
		session.getAttribute("user");
		model.addAttribute("courses", courseService.listAllCourses());
		return "Courses";
	}

	//Show the list of modules enrolled by student and Grades
		@GetMapping("/studentCourses/{id}")
		public String viewSpecificStudentAllCourses(HttpSession session, Model model, @PathVariable("id") int id) {
			
			//Redirect to Error Page if student tries to view other students
			User user=(User) session.getAttribute("user");
			if(user.getRole()==RoleType.STUDENT && user.getId()!=id)
				return "Error";
			
			User student = userService.findUserById(id);
			model.addAttribute("student", student);
			model.addAttribute("listStudentCourses", scService.findStudentCoursesByStudentId(id));
			model.addAttribute("cgpa", UtilityManager.GradesToGPA(scService.findStudentCoursesByStudentId(id)));
			System.out.println(UtilityManager.GradesToGPA(scService.findStudentCoursesByStudentId(id)));
			return "student/student-courses";
		}

	@GetMapping("/search")
	public String searchCourse(HttpSession session, Model model, @Param("keyword") String keyword) {
		
		User user = (User) session.getAttribute("user");
		
		if(keyword == "" || keyword == null) {
			return "redirect:/course/viewCourses/" + user.getId();
		}
		
		List<Course> searchedCourses = courseService.listAll(keyword);
        model.addAttribute("courses", searchedCourses);
        model.addAttribute("keyword", keyword);
        return "Courses";
	}

	//Get list of students in a particular course- viewed by lecturer and admin
	@GetMapping("/{cid}/student-list")
	public String viewCourseStudentList(Model model, @Param("keyword") String keyword, @PathVariable("cid") int cid, HttpSession session) {
		//Redirect to Error Page if student
		User user=(User) session.getAttribute("user");
		if(user.getRole()==RoleType.STUDENT)
			return "Error";
		
		Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);
        List<User> listUsers = scService.listStudentsInCourse(course, keyword);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);
		return "admin/course-student-list-nonEdit";
	}

	//Admin add students to Course
	@GetMapping(value = "/{cid}/addStudentToCourse")
	public String addStudentToCourse(@PathVariable("cid") int cid, @RequestParam("sid") int sid, HttpSession session, 
			@RequestParam("msgHeader") String header, @RequestParam("msgBody") String body) {

		//Redirect to Error Page if not admin
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";
		
		scService.addStudentToCourse(courseService.findCourseById(cid), userService.findStudentById(sid));
		
		try {
		User student = userService.findUserById(sid);
		MailVo mail=new MailVo("PCXGudrew@163.com", student.getEmail(), header, body);
		userService.sendEmailNotification(mail);
		} catch (Exception e) {
			return "Error";
		}
		
		return "forward:/course/"+cid+"/student-list";
	}

	//Admin remove students from Course
	@GetMapping(value = "/{cid}/deleteStudentFromCourse")
	public String deleteStudentFromCourse(@PathVariable("cid") int cid, @RequestParam("sid") int sid, HttpSession session,
			@RequestParam("msgHeader") String header, @RequestParam("msgBody") String body) {
		//Redirect to Error Page if not admin
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";
		
		scService.removeStudentFromCourse(courseService.findCourseById(cid), userService.findStudentById(sid));
		
		try {
		User student = userService.findUserById(sid);
		MailVo mail=new MailVo("PCXGudrew@163.com", student.getEmail(), header, body);
		userService.sendEmailNotification(mail);
		}catch (Exception e) {
			return "Error";
		}
		
		return "forward:/course/"+cid+"/student-list";
	}

	//Admin View -View full list of Students to add/delete from course
	@GetMapping("/{cid}/edit-student-list")
	public String viewCourseStudentList(Model model, @PathVariable("cid") int cid, @Param("keyword") String keyword, HttpSession session) {

		//Redirect to Error Page if not admin
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";		
		
		//get full list of students
		List<User> listUsers = userService.listStudents(keyword);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);
        Course course = courseService.findCourseById(cid);
        model.addAttribute("course", course);

        //Add students in course
        List<User> listStudentsInCourse = scService.listStudentsInCourse(course, keyword);
        model.addAttribute("currentsize",listStudentsInCourse.size());
        model.addAttribute("maxSize",course.getMaxSize());
        model.addAttribute("students", listStudentsInCourse);
		return "admin/course-student-list";
	}

	//Admin Add lecturer to course
	@GetMapping(value = "/{cid}/addLecturerToCourse")
	public String addLecturerToCourse(@PathVariable("cid") int cid, @RequestParam("uid") int uid, HttpSession session,
			@RequestParam("msgHeader") String header, @RequestParam("msgBody") String body) {

		//Redirect to Error Page if not admin
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";				
		
		List<User> lecturer= courseService.findLecturersByCourse(cid);
		
		System.out.println("hi");
		lecturer.add(userService.findLecturerById(uid));
		courseService.addLecturerToCourse(lecturer, cid);
		
		try {
		User lecture = userService.findUserById(uid);
		MailVo mail=new MailVo("PCXGudrew@163.com", lecture.getEmail(), header, body);
		userService.sendEmailNotification(mail);
		} catch (Exception e) {
			return "Error";
		}
		
		return "forward:/course/"+cid+"/edit-lecturer-list";
	}

	//Admin delete lecturer from course
	@GetMapping(value = "/{cid}/deleteLecturerFromCourse")
	public String deleteLecturerFromCourse(@PathVariable("cid") int cid, @RequestParam("uid") int uid, HttpSession session,
			@RequestParam("msgHeader") String header, @RequestParam("msgBody") String body) {

		//Redirect to Error Page if not admin
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";		
		courseService.deleteLecturerFromCourse(userService.findLecturerById(uid), cid);
		
		try {
		User lecture = userService.findUserById(uid);
		MailVo mail=new MailVo("PCXGudrew@163.com", lecture.getEmail(), header, body);
		userService.sendEmailNotification(mail);
		} catch (Exception e) {
		return "Error";
		}

		return "forward:/course/"+cid+"/edit-lecturer-list";
	}

	@GetMapping("/{cid}/edit-lecturer-list")
	public String viewCourseLecturerList(Model model, @PathVariable("cid") int cid, @Param("keyword") String keyword, HttpSession session) {

		//Redirect to Error Page if not admin
		User user=(User) session.getAttribute("user");
		if(user.getRole()!=RoleType.ADMIN)
			return "Error";	
		
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

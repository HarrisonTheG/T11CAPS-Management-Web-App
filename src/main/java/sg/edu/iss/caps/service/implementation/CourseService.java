
  package sg.edu.iss.caps.service.implementation;
  
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
  import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import

  org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.DTO.manageCourse.EditCourseDto;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.StudentCourseRepository;
import sg.edu.iss.caps.repo.UserRepository;
import sg.edu.iss.caps.service.interfaces.ICourse;


  
  @Service public class CourseService implements ICourse{
  
  @Autowired CourseRepository crepo;
  @Autowired StudentCourseRepository screpo;
  @Autowired UserRepository urepo;

  @Override @Transactional(timeout=30, readOnly=true)
  	public List<Course> listAllCourses() {
	  
	return crepo.findAll();
  	}

  @Override
  public Optional<Course> findCourse(int id) {
	  
	  Optional<Course> foundCourse = crepo.findById(id);
	  return foundCourse;
  	}
  
  @Override
  @Transactional
  public void edit(EditCourseDto editCourseDto) throws ParseException {
	Course editcourse=crepo.findById(editCourseDto.getId()).get();
	  editcourse.setName(editCourseDto.getName());
	  editcourse.setCredit(editCourseDto.getCredit());
	  editcourse.setCode(editCourseDto.getCode());
	  editcourse.setDescription(editCourseDto.getDescription());
	 
	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  Date startDate = dateFormat.parse(editCourseDto.getStartDate());
	  long unixStartDate = (long) startDate.getTime()/1000;
	  
	  Date endDate = dateFormat.parse(editCourseDto.getEndDate());
	  long unixEndDate = (long) endDate.getTime()/1000;
	 
	  editcourse.setStartDate(unixStartDate);
	  editcourse.setEndDate(unixEndDate);
	  crepo.save(editcourse);
	  
  }
  
  @Transactional
  public void delete(Course course) {
	  screpo.deleteById(course.getId());
	  crepo.delete(course);
  }
  
  @Transactional
	public Course findCourseById(Integer id) {
		 return crepo.findById(id).get();
	}
  
  @Transactional
  public List<Course> listAll(String keyword) {
		// TODO Auto-generated method stub
		if (keyword != null) {
	        return crepo.search(keyword);
	    }
	    return crepo.findAll();
	}
  
  
  @Override
  public List<Course> findNewCoursesForStudents(int id) {
  	List<Course> allCourses = crepo.findAll();
  	User user = urepo.findById(id).orElse(null);
  	List<Course> enrolledStudentCourses = screpo.findStudentCoursesByStudent(user).stream().map(x -> x.getCourse()).collect(Collectors.toList());
  	
  	List<Course> newCourses = new ArrayList<Course>();
  	
  	for (Course c : allCourses) {
  		if(!enrolledStudentCourses.contains(c)) {
  			newCourses.add(c);
  		}
  	}
  	
  	return newCourses;
  }
    
  
  @Transactional
  public List<Course> findCourseByLecturerId(Integer id){
	  return crepo.findCoursesByUser(id);
  }

  @Transactional
  public void addLecturerToCourse(List<User> lecturers, Integer cid) {
	  crepo.getById(cid).setUser(lecturers);
  }
  
  @Transactional
  public void deleteLecturerFromCourse(User lecturer, Integer cid) {
	  Course selectedCourse = crepo.getById(cid);
	  List<User> existingLecturers = selectedCourse.getUser();
	  existingLecturers.remove(lecturer);
	  selectedCourse.setUser(existingLecturers);
  }  

	
  @Transactional
  public void deleteCourse(Course course) {
		List<Course> c=crepo.findallUsersByCourse(course.getId());
		for(Course i:c)
			crepo.delete(i);
  }


  
  
  }

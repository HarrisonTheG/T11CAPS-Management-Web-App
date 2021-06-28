
  package sg.edu.iss.caps.service.implementation;
  
  import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.beans.factory.annotation.Autowired; import

  org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Course;

import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.service.interfaces.ICourse;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.service.interfaces.ICourse;


  
  @Service public class CourseService implements ICourse{
  
  @Autowired CourseRepository crepo;

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
  public void edit(Course course) {
	Course editcourse=crepo.findById(course.getId()).get();
	  editcourse.setName(course.getName());
	  editcourse.setCredit(course.getCredit());
	  editcourse.setCode(course.getCode());
	  editcourse.setDescription(course.getDescription());
	  editcourse.setStartDate(course.getStartDate());
	  editcourse.setEndDate(course.getEndDate());
	  crepo.save(editcourse);
	  
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
  
  
  }

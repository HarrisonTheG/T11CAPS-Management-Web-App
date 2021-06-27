
  package sg.edu.iss.caps.service.implementation;
  
  import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import
  org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.repo.CourseRepository;
import
  sg.edu.iss.caps.service.interfaces.ICourse;
  
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
  
  
  }
 

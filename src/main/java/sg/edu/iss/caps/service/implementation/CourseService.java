
  package sg.edu.iss.caps.service.implementation;
  
  import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.service.interfaces.ICourse;

  
  @Service public class CourseService implements ICourse{
  
  @Autowired CourseRepository crepo;
  
  @Transactional
	public Course findCourseById(Integer id) {
		 return crepo.findById(id).get();
	}
  
  public List<Course> listAll(String keyword) {
		// TODO Auto-generated method stub
		if (keyword != null) {
	        return crepo.search(keyword);
	    }
	    return crepo.findAll();
	}
  
  }
 

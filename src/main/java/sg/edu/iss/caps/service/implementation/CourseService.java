
  package sg.edu.iss.caps.service.implementation;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.service.interfaces.ICourse;

  
  @Service public class CourseService implements ICourse{
  
  @Autowired CourseRepository crepo;
  
  @Transactional
	public Course findCourseById(Integer id) {
		 return crepo.findById(id).get();
	}
  
  }
 

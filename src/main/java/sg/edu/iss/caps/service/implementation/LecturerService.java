  

  package sg.edu.iss.caps.service.implementation;
  
  import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.LecturerRepository; 
  import sg.edu.iss.caps.service.interfaces.ILecturer;
  
 @Service public class LecturerService implements ILecturer{
  
  @Autowired LecturerRepository lrepo;

@Override
public List<User> listAll(String keyword) {
	// TODO Auto-generated method stub
	if (keyword != null) {
        return lrepo.search(keyword);
    }
    return lrepo.findAll();
}
 }

 
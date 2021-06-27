package sg.edu.iss.caps.service.implementation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.UserRepository;
import sg.edu.iss.caps.service.interfaces.IUser;
import sg.edu.iss.caps.utility.UtilityManager;

@Service
public class UserService implements IUser {
	
	@Autowired UserRepository urepo;
	
	@Override
	public User findUser(@Valid String email, String password, String identity) {
		
		User user = urepo.findByEmailAndPassword(email, password);
		if(user != null) {
			if (UtilityManager.checkIdentity(user, identity) != "error")
				return user;
		}
			
		return null;
	}
	
	@Transactional
	public User findStudentById(Integer id) {
		 return urepo.findStudent(id);
	}
	
	@Transactional
	public User findLecturerById(Integer id) {
		 return urepo.findLecturer(id);
	}
}

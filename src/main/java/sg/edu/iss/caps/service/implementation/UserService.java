package sg.edu.iss.caps.service.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.UserRepository;
import sg.edu.iss.caps.service.interfaces.ILecturer;
import sg.edu.iss.caps.service.interfaces.IUser;
import sg.edu.iss.caps.utility.UtilityManager;

@Service
public class UserService implements IUser,ILecturer {
	
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
	
	
	public List<User> listAll(String keyword) {
		// TODO Auto-generated method stub
		if (keyword != null) {
	        return urepo.search(keyword);
	    }
	    return urepo.findAll();
	}
}

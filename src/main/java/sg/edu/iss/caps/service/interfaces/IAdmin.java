package sg.edu.iss.caps.service.interfaces;

import java.util.List;

import sg.edu.iss.caps.model.User;

public interface IAdmin {
	
	public List<User> listAll(String keyword);
	
}

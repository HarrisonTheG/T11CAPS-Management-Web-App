package sg.edu.iss.caps.service.interfaces;

import java.util.List;

import sg.edu.iss.caps.model.User;

public interface ILecturer {
	
	public List<User> listAll(String keyword);
	
}
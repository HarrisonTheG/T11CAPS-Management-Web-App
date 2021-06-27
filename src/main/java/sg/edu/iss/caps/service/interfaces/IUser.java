package sg.edu.iss.caps.service.interfaces;



import javax.validation.Valid;

import sg.edu.iss.caps.model.User;

public interface IUser {


	public User findUser(@Valid String email, String password, String identity);
	
	
}

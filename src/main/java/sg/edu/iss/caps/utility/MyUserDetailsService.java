package sg.edu.iss.caps.utility;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.UserRepository;

//This class will search through the DB to check if there is valid user
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository urepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Optional<User> user=urepo.findByEmail(userName);
		
		user.orElseThrow(()->new UsernameNotFoundException("Not Found"+userName));
		
		return user.map(MyUserDetails::new).get();
	}
	
	
}

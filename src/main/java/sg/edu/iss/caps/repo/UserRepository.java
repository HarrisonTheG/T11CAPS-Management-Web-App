package sg.edu.iss.caps.repo;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	

	User findByEmailAndPassword(@Valid String email, String password);
	
}

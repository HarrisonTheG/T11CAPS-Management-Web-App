package sg.edu.iss.caps.repo;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.caps.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findByEmail(String userName); //tell jpa that this service needs implementation in users.
	//method is given a username, JPA do a method username, finds a given user.

	User findByEmailAndPassword(@Valid String email, String password);
	
	@Query("SELECT u FROM User u WHERE CONCAT(u.id, ' ', u.firstname, ' ', u.surname, ' ', u.email) LIKE %?1%")
	public List<User> search(String keyword);
	
}

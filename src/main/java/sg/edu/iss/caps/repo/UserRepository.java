package sg.edu.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}

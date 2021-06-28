
 package sg.edu.iss.caps.repo;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import sg.edu.iss.caps.model.User;
 
 @Repository public interface StudentRepository extends JpaRepository <User,Integer>{
 
  }
 

 package sg.edu.iss.caps.repo;
  
  import org.springframework.data.jpa.repository.JpaRepository; import
  org.springframework.stereotype.Repository;

import sg.edu.iss.caps.model.User;
  
  
  
  @Repository public interface LecturerRepository extends
  JpaRepository<User, Integer>{
  
 }
 
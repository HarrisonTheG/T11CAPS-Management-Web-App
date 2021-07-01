package sg.edu.iss.caps.repository;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.caps.T11CapsApplication;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = T11CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserUnitTests {

	@Autowired
	private UserRepository urepo;
	
	@Test
	@Order(1)
	public void testCreateUser() {
	  String img="https://www.bootdey.com/img/Content/avatar/avatar10.png";
	  String email="18011979@qq.com";		
	  User u= new User("Jay","Zhou",email,"123",img,18011979);	   
	  User saved=urepo.save(u);
	  assertNotNull(saved);
	}
	
	@Test
	@Order(2)
	public void testSearchUserByKeywords() {
		String keywords="z";
		List<User> saved=urepo.search(keywords);
		assertTrue(saved.size()>0);
		
	}
	@Test
	@Order(3)
	public void testFindUserByRoleType() {
		Integer userId=1;
		User saved=urepo.findUserByRoleType(userId, RoleType.STUDENT);
		assertNotNull(saved);
		
	}
	
	@Test
	@Order(4)
	public void testSearchByRoleType() {		
		String keywords="t";
		List<User> saved=urepo.searchByRoleType(RoleType.LECTURER,keywords);
		assertTrue(saved.size()>0);
		
	}
	
	@Test
	@Order(5)
	public void testListAllInRolee() {		
		List<User> saved=urepo.listAllInRole(RoleType.LECTURER);
		assertTrue(saved.size()>0);
		
	}
//	@Test
//	@Order(6)
//	public void testDeleteUser() {
//		String kw="zhou";
//		User selected=urepo.findUserByRoleType(11,Null);
//		Lise<User> selected2=urepo.delete(selected);
//		assertTrue(selected2.size()>0);
//	}
	
	
	
	
	
}

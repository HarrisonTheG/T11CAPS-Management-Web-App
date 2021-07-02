package sg.edu.iss.caps.repository;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		List<User> result=new ArrayList<User>();
		
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		
		User u1=new User();
		u1.setFirstname("Zavier");
		u1.setEmail("ZavierLim@nus.edu.sg");
		u1.setEnrollmentDate(epoch);
		u1.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u1.setRole(RoleType.STUDENT);
		u1.setSurname("Lim");
		u1.setPassword("123");
		u1.setId(1);
		
		User u10=new User();
		u10.setFirstname("Tian");
		u10.setEmail("ZhangTian@nus.edu.sg");
		u10.setEnrollmentDate(epoch);
		u10.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar10.png");
		u10.setRole(RoleType.STUDENT);
		u10.setSurname("Zhang");
		u10.setPassword("123");
		u10.setId(10);
		
		User u18=new User();
		u18.setFirstname("Aziz");
		u18.setEmail("azizansari@nus.edu.sg");
		u18.setEnrollmentDate(epoch);
		u18.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u18.setRole(RoleType.STUDENT);
		u18.setSurname("Ansari");
		u18.setPassword("123");
		u18.setId(18);
		
		User u20=new User();
		u20.setFirstname("Hasan");
		u20.setEmail("hasanafzal@nus.edu.sg");
		u20.setEnrollmentDate(epoch);
		u20.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u20.setRole(RoleType.STUDENT);
		u20.setSurname("Afzal");
		u20.setPassword("123");
		u20.setId(20);
		
		String img="https://www.bootdey.com/img/Content/avatar/avatar10.png";
		String email="18011979@qq.com";		
		User u= new User("Jay","Zhou",email,"123",img,18011979);
		u.setId(21);
		
		result.add(u1);
		result.add(u10);
		result.add(u18);
		result.add(u20);
		result.add(u);
		
//		for(User us : saved)
//			System.out.println(us);
		
		assertTrue(result.containsAll(saved) && saved.containsAll(result));
	}
	@Test
	@Order(3)
	public void testFindUserByRoleType_Student() {
		Integer userId=1;
		User saved=urepo.findUserByRoleType(userId, RoleType.STUDENT);
		
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		
		User u1=new User();
		u1.setFirstname("Zavier");
		u1.setEmail("ZavierLim@nus.edu.sg");
		u1.setEnrollmentDate(epoch);
		u1.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u1.setRole(RoleType.STUDENT);
		u1.setSurname("Lim");
		u1.setPassword("123");
		u1.setId(1);
		assertTrue(saved.equals(u1));			
	}
	
	@Test
	@Order(4)
	public void testSearchByRoleType() {		
		String keywords="t";
		List<User> saved=urepo.searchByRoleType(RoleType.LECTURER,keywords);
		List<User> result=new ArrayList<User>();
		
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		
		User u4=new User();
		u4.setFirstname("Tin");
		u4.setEmail("Tin@nus.edu.sg");
		u4.setEnrollmentDate(epoch);
		u4.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar4.png");
		u4.setRole(RoleType.LECTURER);
		u4.setSurname("Ngyuen");
		u4.setPassword("123");
		u4.setId(4);
		
		User u5=new User();
		u5.setFirstname("Cher Wah");
		u5.setEmail("Cher_Wah@nus.edu.sg");
		u5.setEnrollmentDate(epoch);
		u5.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar5.png");
		u5.setRole(RoleType.LECTURER);
		u5.setSurname("Tan");
		u5.setPassword("123");
		u5.setId(5);
		
		User u6=new User();
		u6.setFirstname("Feliciatas");
		u6.setEmail("Feliciatas@nus.edu.sg");
		u6.setEnrollmentDate(epoch);
		u6.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar6.png");
		u6.setRole(RoleType.LECTURER);
		u6.setSurname("Tan");
		u6.setPassword("123");
		u6.setId(6);
		
		result.add(u4);
		result.add(u5);
		result.add(u6);
		
		assertTrue(result.containsAll(saved) && saved.containsAll(result));
	}
	
	@Test
	@Order(5)
	public void testListAllInRolee() {		
		List<User> saved=urepo.listAllInRole(RoleType.LECTURER);
		List<User> result=new ArrayList<User>();
		
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		
		User u4=new User();
		u4.setFirstname("Tin");
		u4.setEmail("Tin@nus.edu.sg");
		u4.setEnrollmentDate(epoch);
		u4.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar4.png");
		u4.setRole(RoleType.LECTURER);
		u4.setSurname("Ngyuen");
		u4.setPassword("123");
		u4.setId(4);
		
		User u5=new User();
		u5.setFirstname("Cher Wah");
		u5.setEmail("Cher_Wah@nus.edu.sg");
		u5.setEnrollmentDate(epoch);
		u5.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar5.png");
		u5.setRole(RoleType.LECTURER);
		u5.setSurname("Tan");
		u5.setPassword("123");
		u5.setId(5);
		
		User u6=new User();
		u6.setFirstname("Feliciatas");
		u6.setEmail("Feliciatas@nus.edu.sg");
		u6.setEnrollmentDate(epoch);
		u6.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar6.png");
		u6.setRole(RoleType.LECTURER);
		u6.setSurname("Tan");
		u6.setPassword("123");
		u6.setId(6);
		
		result.add(u4);
		result.add(u5);
		result.add(u6);
		
		assertTrue(result.containsAll(saved) && saved.containsAll(result));
		
	}
	@Test
	@Order(6)
	public void testSearchUserById() {
		String id="16";
		List<User> saved=urepo.search(id);
		List<User> result=new ArrayList<User>();
		
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		
		User u16=new User();
		u16.setFirstname("Rahul");
		u16.setEmail("rahulvaidya@nus.edu.sg");
		u16.setEnrollmentDate(epoch);
		u16.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u16.setRole(RoleType.STUDENT);
		u16.setSurname("Vaidya");
		u16.setPassword("123");
		u16.setId(16);
		
		assertTrue(result.containsAll(saved) && saved.containsAll(result));		
	}
	@Test
	@Order(7)
	public void testSearchUserByFirstName() {
		String name="Zavier";
		List<User> saved=urepo.search(name);
		List<User> result=new ArrayList<User>();
	
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		
		User u1=new User();
		u1.setFirstname("Zavier");
		u1.setEmail("ZavierLim@nus.edu.sg");
		u1.setEnrollmentDate(epoch);
		u1.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u1.setRole(RoleType.STUDENT);
		u1.setSurname("Lim");
		u1.setPassword("123");
		u1.setId(1);
		
		result.add(u1);
		
		assertTrue(result.containsAll(saved) && saved.containsAll(result));
	}
	@Test
	@Order(8)
	public void testSearchUserBySurName() {
		String name="Zhang";
		List<User> saved=urepo.search(name);
		List<User> result=new ArrayList<User>();
		
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		
		User u10=new User();
		u10.setFirstname("Tian");
		u10.setEmail("ZhangTian@nus.edu.sg");
		u10.setEnrollmentDate(epoch);
		u10.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar10.png");
		u10.setRole(RoleType.STUDENT);
		u10.setSurname("Zhang");
		u10.setPassword("123");
		u10.setId(10);
		
		result.add(u10);
		
		assertTrue(result.containsAll(saved) && saved.containsAll(result));
	}
	@Test
	@Order(9)
	public void testSearchUserByEmail() {
		String email="ZavierLim@nus.edu.sg";
		List<User> saved=urepo.search(email);
		List<User> result=new ArrayList<User>();
		
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		
		User u1=new User();
		u1.setFirstname("Zavier");
		u1.setEmail("ZavierLim@nus.edu.sg");
		u1.setEnrollmentDate(epoch);
		u1.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u1.setRole(RoleType.STUDENT);
		u1.setSurname("Lim");
		u1.setPassword("123");
		u1.setId(1);
		
		result.add(u1);
		
		assertTrue(result.containsAll(saved) && saved.containsAll(result));
	}
	@Test
	@Order(10)
	public void testFindUserByRoleType_Lecturer() {
		Integer userId=4;
		User saved=urepo.findUserByRoleType(userId, RoleType.LECTURER);
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		User u4=new User();
		u4.setFirstname("Tin");
		u4.setEmail("Tin@nus.edu.sg");
		u4.setEnrollmentDate(epoch);
		u4.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar4.png");
		u4.setRole(RoleType.LECTURER);
		u4.setSurname("Ngyuen");
		u4.setPassword("123");
		u4.setId(4);
		assertTrue(saved.equals(u4));		
	}
	@Test
	@Order(11)
	public void testFindUserByRoleType_Admin() {
		Integer userId=7;
		User saved=urepo.findUserByRoleType(userId, RoleType.ADMIN);
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		User u7=new User();
		u7.setFirstname("Megan");
		u7.setEmail("megan@nus.edu.sg");
		u7.setEnrollmentDate(epoch);
		u7.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar3.png");
		u7.setRole(RoleType.ADMIN);
		u7.setSurname("Lee");
		u7.setPassword("123");
		u7.setId(7);
		
		assertTrue(saved.equals(u7));		
	}
	
	
//	@Test
//	@Order(12)
//	public void testDeleteUser() {
//		String kw="Zhou";
//		
//		List<User> selected=urepo.search(kw);
//		urepo.deleteById(21);
//		assertTrue(selected.size()==0);
//	}
	
	
	
	
	
}

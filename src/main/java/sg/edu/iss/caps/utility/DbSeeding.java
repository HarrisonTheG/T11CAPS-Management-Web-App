package sg.edu.iss.caps.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.repo.CourseRepository;
import sg.edu.iss.caps.repo.StudentCourseRepository;
import sg.edu.iss.caps.repo.UserRepository;

@Service
public class DbSeeding {
	@Autowired
	CourseRepository crepo;
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	StudentCourseRepository screpo;
	
	@Transactional
	public void SeedDBWithUsersAndCourses() {
		LocalDateTime s1enrolment=LocalDateTime.of(2021,2,1,0,0,0);
		ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime s1dtz=ZonedDateTime.of(s1enrolment, sgZone);
		long epoch=s1dtz.toEpochSecond();
		
		User u1=new User();
		u1.setFirstname("Zavier");
		u1.setEmail("ZavierLim@nus.edu.sg");
		u1.setEnrollmentDate(epoch);
		u1.setImgUrl("http://zavierlim.com");
		u1.setRole(RoleType.STUDENT);
		u1.setSurname("Lim");
		u1.setPassword("123");
		
		User u2=new User();
		u2.setFirstname("Harrison");
		u2.setEmail("Harrison@nus.edu.sg");
		u2.setEnrollmentDate(epoch);
		u2.setImgUrl("http://Harrison.com");
		u2.setRole(RoleType.STUDENT);
		u2.setSurname("Gonaewan");
		u2.setPassword("123");
		
		User u3=new User();
		u3.setFirstname("Wan Ling");
		u3.setEmail("WanLing@nus.edu.sg");
		u3.setEnrollmentDate(epoch);
		u3.setImgUrl("http://Wanling.com");
		u3.setRole(RoleType.STUDENT);
		u3.setSurname("Thoo");
		u3.setPassword("123");
		
		User u4=new User();
		u4.setFirstname("Tin");
		u4.setEmail("Tin@nus.edu.sg");
		u4.setEnrollmentDate(epoch);
		u4.setImgUrl("http://Tin.com");
		u4.setRole(RoleType.LECTURER);
		u4.setSurname("Ngyuen");
		u4.setPassword("123");
		
		User u5=new User();
		u5.setFirstname("Cher Wah");
		u5.setEmail("Cher_Wah@nus.edu.sg");
		u5.setEnrollmentDate(epoch);
		u5.setImgUrl("http://CherWah.com");
		u5.setRole(RoleType.LECTURER);
		u5.setSurname("Tan");
		u5.setPassword("123");
		
		User u6=new User();
		u6.setFirstname("Feliciatas");
		u6.setEmail("Feliciatas@nus.edu.sg");
		u6.setEnrollmentDate(epoch);
		u6.setImgUrl("http://felic.com");
		u6.setRole(RoleType.LECTURER);
		u6.setSurname("Tan");
		u6.setPassword("123");
		
		User u7=new User();
		u7.setFirstname("Megan");
		u7.setEmail("megan@nus.edu.sg");
		u7.setEnrollmentDate(epoch);
		u7.setImgUrl("http://megan.com");
		u7.setRole(RoleType.ADMIN);
		u7.setSurname("Lee");
		u7.setPassword("123");
		
		urepo.save(u1);urepo.save(u2);urepo.save(u3);urepo.save(u4);urepo.save(u5);urepo.save(u6);urepo.save(u7);
		
		//Start Date of Sem1 and 2
		LocalDateTime Sem1ModuleStart=LocalDateTime.of(2021,2,1,0,0,0);
		LocalDateTime Sem2ModuleStart=LocalDateTime.of(2021,5,1,0,0,0);
		//ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime Sem1Start=ZonedDateTime.of(Sem1ModuleStart,sgZone);
		ZonedDateTime Sem2Start=ZonedDateTime.of(Sem2ModuleStart,sgZone);
		long Sem2EpochStart=Sem2Start.toEpochSecond();
		long Sem1EpochStart=Sem1Start.toEpochSecond();
		
		//End Date of Sem1 and 2
		LocalDateTime Sem1ModuleEnd=LocalDateTime.of(2021,4,30,0,0,0);
		LocalDateTime Sem2ModuleEnd=LocalDateTime.of(2021,7,31,0,0,0);

		ZonedDateTime Sem1End=ZonedDateTime.of(Sem1ModuleEnd,sgZone);
		ZonedDateTime Sem2End=ZonedDateTime.of(Sem2ModuleEnd,sgZone);
		
		long Sem2EpochEnd=Sem1End.toEpochSecond();
		long Sem1EpochEnd=Sem2End.toEpochSecond();
		
		
		Course c1=new Course();
		c1.setCredit(4);
		c1.setDescription("Teach Students how to design Software");
		c1.setCode("SA4101");
		c1.setStartDate(Sem1EpochStart);
		c1.setEndDate(Sem1EpochEnd);
		c1.setMaxSize(3);
		c1.setName("Software Analysis And Design");
		crepo.save(c1);
		
		Course c2=new Course();
		c2.setCredit(4);
		c2.setDescription("Teach Students how to Code C#");
		c2.setCode("SA4102");
		c2.setStartDate(Sem1EpochStart);
		c2.setEndDate(Sem1EpochEnd);
		c2.setMaxSize(3);
		c2.setName("Enterprise Solutions Design and Development");
		crepo.save(c2);
		
		Course c3=new Course();
		c3.setCredit(4);
		c3.setDescription("Teach Students how to design Digital Products");
		c3.setCode("SA4104");
		c3.setStartDate(Sem2EpochStart);
		c3.setEndDate(Sem2EpochEnd);
		c3.setMaxSize(3);
		c3.setName("Digital Product Management");
		crepo.save(c3);		
	}
	
	@Transactional
	public void SeedDBforCoursesTeachedandEnrolled() {
		//set student for course 1
		Student_Course sc1=new Student_Course();
		sc1.setCourse(crepo.getById(1));
		sc1.setUser(urepo.getById(1));
		Student_Course sc2=new Student_Course();
		sc2.setCourse(crepo.getById(1));
		sc2.setUser(urepo.getById(2));
		Student_Course sc3=new Student_Course();
		sc3.setCourse(crepo.getById(1));
		sc3.setUser(urepo.getById(3));

		screpo.save(sc1);screpo.save(sc2); screpo.save(sc3);
		//set student for course 2
		Student_Course sc4=new Student_Course();
		sc4.setCourse(crepo.getById(2));
		sc4.setUser(urepo.getById(1));
		Student_Course sc5=new Student_Course();
		sc5.setCourse(crepo.getById(2));
		sc5.setUser(urepo.getById(2));
		Student_Course sc6=new Student_Course();
		sc6.setCourse(crepo.getById(2));
		sc6.setUser(urepo.getById(3));
		
		screpo.save(sc4);screpo.save(sc5); screpo.save(sc6);
		
		//set student for course 3
		Student_Course sc7=new Student_Course();
		sc7.setCourse(crepo.getById(3));
		sc7.setUser(urepo.getById(1));
		Student_Course sc8=new Student_Course();
		sc8.setCourse(crepo.getById(3));
		sc8.setUser(urepo.getById(2));
		Student_Course sc9=new Student_Course();
		sc9.setCourse(crepo.getById(3));
		sc9.setUser(urepo.getById(3));

		screpo.save(sc7);screpo.save(sc8); screpo.save(sc9);

		//add lecturer to course1 
		List<User> LectTeach1=new ArrayList<User>();
		LectTeach1.add(urepo.getById(4));
		LectTeach1.add(urepo.getById(5));
		crepo.getById(1).setUser(LectTeach1);
		crepo.save(crepo.getById(1));	
		
		//add lecturer to course2
		List<User> LectTeach2=new ArrayList<User>();
		LectTeach2.add(urepo.getById(4));
		LectTeach2.add(urepo.getById(5));
		crepo.getById(2).setUser(LectTeach2);
		crepo.save(crepo.getById(2));	
		
		//add lecturer to course3
		List<User> LectTeach3=new ArrayList<User>();
		LectTeach3.add(urepo.getById(6));
		crepo.getById(3).setUser(LectTeach3);
		crepo.save(crepo.getById(3));
	}
}

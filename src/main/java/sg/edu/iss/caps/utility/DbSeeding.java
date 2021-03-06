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
		u1.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u1.setRole(RoleType.STUDENT);
		u1.setSurname("Lim");
		u1.setPassword("123");
		
		User u2=new User();
		u2.setFirstname("Harrison");
		u2.setEmail("e0696695@u.nus.edu");
		u2.setEnrollmentDate(epoch);
		u2.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar2.png");
		u2.setRole(RoleType.STUDENT);
		u2.setSurname("Goenawan");
		u2.setPassword("123");
		
		User u3=new User();
		u3.setFirstname("Wan Ling");
		u3.setEmail("e0004981@u.nus.edu");
		u3.setEnrollmentDate(epoch);
		u3.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar3.png");
		u3.setRole(RoleType.STUDENT);
		u3.setSurname("Thoo");
		u3.setPassword("123");
		
		User u4=new User();
		u4.setFirstname("Tin");
		u4.setEmail("wanling2102@gmail.com");
		u4.setEnrollmentDate(epoch);
		u4.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar4.png");
		u4.setRole(RoleType.LECTURER);
		u4.setSurname("Nguyen");
		u4.setPassword("123");
		
		User u5=new User();
		u5.setFirstname("Cher Wah");
		u5.setEmail("Cher_Wah@nus.edu.sg");
		u5.setEnrollmentDate(epoch);
		u5.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar5.png");
		u5.setRole(RoleType.LECTURER);
		u5.setSurname("Tan");
		u5.setPassword("123");
		
		User u6=new User();
		u6.setFirstname("Felicitas");
		u6.setEmail("Felicitas@nus.edu.sg");
		u6.setEnrollmentDate(epoch);
		u6.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar6.png");
		u6.setRole(RoleType.LECTURER);
		u6.setSurname("Tan");
		u6.setPassword("123");
		
		User u7=new User();
		u7.setFirstname("Megan");
		u7.setEmail("megan@nus.edu.sg");
		u7.setEnrollmentDate(epoch);
		u7.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar3.png");
		u7.setRole(RoleType.ADMIN);
		u7.setSurname("Lee");
		u7.setPassword("123");
		
		User u8=new User();
		u8.setFirstname("Kong Jia");
		u8.setEmail("QiKongJia@nus.edu.sg");
		u8.setEnrollmentDate(epoch);
		u8.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar8.png");
		u8.setRole(RoleType.STUDENT);
		u8.setSurname("Qi");
		u8.setPassword("123");
		
		User u9=new User();
		u9.setFirstname("Chen Xiao");
		u9.setEmail("Chenxiao@nus.edu.sg");
		u9.setEnrollmentDate(epoch);
		u9.setImgUrl("https://www.bootdey.com/img/Content/avartar/avatar9.png");
		u9.setRole(RoleType.STUDENT);
		u9.setSurname("Pan");
		u9.setPassword("123");
		
		
		User u10=new User();
		u10.setFirstname("Tian");
		u10.setEmail("ZhangTian@nus.edu.sg");
		u10.setEnrollmentDate(epoch);
		u10.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar10.png");
		u10.setRole(RoleType.STUDENT);
		u10.setSurname("Zhang");
		u10.setPassword("123");
		
		User u11=new User();
		u11.setFirstname("Vanessa");
		u11.setEmail("vanessalim@nus.edu.sg");
		u11.setEnrollmentDate(epoch);
		u11.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar11.png");
		u11.setRole(RoleType.STUDENT);
		u11.setSurname("Lim");
		u11.setPassword("123");
		
		User u12=new User();
		u12.setFirstname("Kimberly");
		u12.setEmail("kimberlytan@nus.edu.sg");
		u12.setEnrollmentDate(epoch);
		u12.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar3.png");
		u12.setRole(RoleType.STUDENT);
		u12.setSurname("Tan");
		u12.setPassword("123");
		
		User u13=new User();
		u13.setFirstname("Sarah");
		u13.setEmail("sarahlee@nus.edu.sg");
		u13.setEnrollmentDate(epoch);
		u13.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar3.png");
		u13.setRole(RoleType.STUDENT);
		u13.setSurname("Lee");
		u13.setPassword("123");
		
		User u14=new User();
		u14.setFirstname("Vallei");
		u14.setEmail("valleichopra@nus.edu.sg");
		u14.setEnrollmentDate(epoch);
		u14.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar3.png");
		u14.setRole(RoleType.STUDENT);
		u14.setSurname("Chopra");
		u14.setPassword("123");
		
		User u15=new User();
		u15.setFirstname("Nadia");
		u15.setEmail("nadiaahmed@nus.edu.sg");
		u15.setEnrollmentDate(epoch);
		u15.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar3.png");
		u15.setRole(RoleType.STUDENT);
		u15.setSurname("Ahmed");
		u15.setPassword("123");
		
		User u16=new User();
		u16.setFirstname("Rahul");
		u16.setEmail("rahulvaidya@nus.edu.sg");
		u16.setEnrollmentDate(epoch);
		u16.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u16.setRole(RoleType.STUDENT);
		u16.setSurname("Vaidya");
		u16.setPassword("123");
		
		User u17=new User();
		u17.setFirstname("Bo Gum");
		u17.setEmail("parkbogum@nus.edu.sg");
		u17.setEnrollmentDate(epoch);
		u17.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u17.setRole(RoleType.STUDENT);
		u17.setSurname("Park");
		u17.setPassword("123");
		
		User u18=new User();
		u18.setFirstname("Aziz");
		u18.setEmail("azizansari@nus.edu.sg");
		u18.setEnrollmentDate(epoch);
		u18.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u18.setRole(RoleType.STUDENT);
		u18.setSurname("Ansari");
		u18.setPassword("123");
		
		User u19=new User();
		u19.setFirstname("Henry");
		u19.setEmail("henryliu@nus.edu.sg");
		u19.setEnrollmentDate(epoch);
		u19.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u19.setRole(RoleType.STUDENT);
		u19.setSurname("Liu");
		u19.setPassword("123");
		
		User u20=new User();
		u20.setFirstname("Hasan");
		u20.setEmail("hasanafzal@nus.edu.sg");
		u20.setEnrollmentDate(epoch);
		u20.setImgUrl("https://www.bootdey.com/img/Content/avatar/avatar1.png");
		u20.setRole(RoleType.STUDENT);
		u20.setSurname("Afzal");
		u20.setPassword("123");
		
		urepo.save(u1);urepo.save(u2);urepo.save(u3);urepo.save(u4);urepo.save(u5);urepo.save(u6);urepo.save(u7);
		urepo.save(u8);urepo.save(u9);urepo.save(u10);
		urepo.save(u11);urepo.save(u12);urepo.save(u13);urepo.save(u14);urepo.save(u15);
		urepo.save(u16);urepo.save(u17);urepo.save(u18);urepo.save(u19);urepo.save(u20);
		//Start Date of Sem1 and 2
		LocalDateTime Sem1ModuleStart=LocalDateTime.of(2021,2,1,0,0,0);
		LocalDateTime Sem2ModuleStart=LocalDateTime.of(2021,5,1,0,0,0);
		//ZoneId sgZone=ZoneId.of("Asia/Singapore");
		ZonedDateTime Sem1Start=ZonedDateTime.of(Sem1ModuleStart,sgZone);
		ZonedDateTime Sem2Start=ZonedDateTime.of(Sem2ModuleStart,sgZone);

		long Sem1EpochStart=Sem1Start.toEpochSecond();
		long Sem2EpochStart=Sem2Start.toEpochSecond();
		
		//End Date of Sem1 and 2
		LocalDateTime Sem1ModuleEnd=LocalDateTime.of(2021,4,30,0,0,0);
		LocalDateTime Sem2ModuleEnd=LocalDateTime.of(2021,7,31,0,0,0);

		ZonedDateTime Sem1End=ZonedDateTime.of(Sem1ModuleEnd,sgZone);
		ZonedDateTime Sem2End=ZonedDateTime.of(Sem2ModuleEnd,sgZone);
		
		long Sem1EpochEnd=Sem1End.toEpochSecond();
		long Sem2EpochEnd=Sem2End.toEpochSecond();		
		
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
		
		Course c4=new Course();
		c4.setCredit(4);
		c4.setDescription("To Teach students Java and React");
		c4.setCode("SA4105");
		c4.setStartDate(Sem2EpochStart);
		c4.setEndDate(Sem2EpochEnd);
		c4.setMaxSize(3);
		c4.setName("Web Application Development");
		crepo.save(c4);		
		
		Course c5=new Course();
		c5.setCredit(4);
		c5.setDescription("This module teaches student how to create an android application");
		c5.setCode("SA4108");
		c5.setStartDate(Sem2EpochStart);
		c5.setEndDate(Sem2EpochEnd);
		c5.setMaxSize(3);
		c5.setName("Mobile Application Development");
		crepo.save(c5);
		
		
		Course c6=new Course();
		c6.setCredit(4);
		c6.setDescription("This is the final AD Project module");
		c6.setCode("SA4110");
		c6.setStartDate(Sem2EpochStart);
		c6.setEndDate(Sem2EpochEnd);
		c6.setMaxSize(3);
		c6.setName("AD Project");
		crepo.save(c6);		
		
		
		Course c7=new Course();
		c7.setCredit(4);
		c7.setDescription("This Module teaches students how to use JavaScript");
		c7.setCode("SA4111");
		c7.setStartDate(Sem2EpochStart);
		c7.setEndDate(Sem2EpochEnd);
		c7.setMaxSize(3);
		c7.setName("JavaScript 101");
		crepo.save(c7);		
		
		
		Course c8=new Course();
		c8.setCredit(4);
		c8.setDescription("This module teaches students algorithm and data structure");
		c8.setCode("SA4112");
		c8.setStartDate(Sem2EpochStart);
		c8.setEndDate(Sem2EpochEnd);
		c8.setMaxSize(3);
		c8.setName("Algorithm and Data Structures Part 1");
		crepo.save(c8);		
		
		
		Course c9=new Course();
		c9.setCredit(4);
		c9.setDescription("This module teaches students advanced algorithms and data structure");
		c9.setCode("SA4113");
		c9.setStartDate(Sem2EpochStart);
		c9.setEndDate(Sem2EpochEnd);
		c9.setMaxSize(3);
		c9.setName("Algorithm and Data Structures Part 2");
		crepo.save(c9);		
		
		
		Course c10=new Course();
		c10.setCredit(4);
		c10.setDescription("This module teaches Students SQL and database knowledge");
		c10.setCode("SA4114");
		c10.setStartDate(Sem2EpochStart);
		c10.setEndDate(Sem2EpochEnd);
		c10.setMaxSize(3);
		c10.setName("SQL and Databases");
		crepo.save(c10);		
		
		Course c11=new Course();
		c11.setCredit(4);
		c11.setDescription("This module teaches Students the common principles and concepts in computer systems");
		c11.setCode("SA4115");
		c11.setStartDate(Sem2EpochStart);
		c11.setEndDate(Sem2EpochEnd);
		c11.setMaxSize(3);
		c11.setName("Computer Systems and Applications");
		crepo.save(c11);	
	}
	
	@Transactional
	public void SeedDBforCoursesTeachedandEnrolled() {
		//set student for course 1
		Student_Course sc1=new Student_Course();
		sc1.setCourse(crepo.getById(1));
		sc1.setUser(urepo.getById(1));
		sc1.setGrade(88);
		Student_Course sc2=new Student_Course();
		sc2.setCourse(crepo.getById(1));
		sc2.setUser(urepo.getById(2));
		sc2.setGrade(76);
		Student_Course sc3=new Student_Course();
		sc3.setCourse(crepo.getById(1));
		sc3.setUser(urepo.getById(3));
		sc3.setGrade(55);

		screpo.save(sc1);screpo.save(sc2); screpo.save(sc3);
		//set student for course 2
		Student_Course sc4=new Student_Course();
		sc4.setCourse(crepo.getById(2));
		sc4.setUser(urepo.getById(1));
		sc4.setGrade(92);
		Student_Course sc5=new Student_Course();
		sc5.setCourse(crepo.getById(2));
		sc5.setUser(urepo.getById(2));
		sc5.setGrade(53);
		Student_Course sc6=new Student_Course();
		sc6.setCourse(crepo.getById(2));
		sc6.setUser(urepo.getById(3));
		sc6.setGrade(85);
		
		screpo.save(sc4);screpo.save(sc5); screpo.save(sc6);
		
		//set student for course 3
		Student_Course sc7=new Student_Course();
		sc7.setCourse(crepo.getById(3));
		sc7.setUser(urepo.getById(1));
		sc7.setGrade(77);
		Student_Course sc8=new Student_Course();
		sc8.setCourse(crepo.getById(3));
		sc8.setUser(urepo.getById(2));
		sc8.setGrade(50);
		Student_Course sc9=new Student_Course();
		sc9.setCourse(crepo.getById(3));
		sc9.setUser(urepo.getById(3));
		sc9.setGrade(62);

		screpo.save(sc7);screpo.save(sc8); screpo.save(sc9);
		
		//set student for course 4
		Student_Course sc10=new Student_Course();
		sc10.setCourse(crepo.getById(4));
		sc10.setUser(urepo.getById(1));
		sc10.setGrade(95);
		Student_Course sc11=new Student_Course();
		sc11.setCourse(crepo.getById(4));
		sc11.setUser(urepo.getById(2));
		sc11.setGrade(43);
		Student_Course sc12=new Student_Course();
		sc12.setCourse(crepo.getById(4));
		sc12.setUser(urepo.getById(3));
		sc12.setGrade(65);

		screpo.save(sc10);screpo.save(sc11); screpo.save(sc12);
		
		//set student for course 6
		Student_Course sc13=new Student_Course();
		sc13.setCourse(crepo.getById(6));
		sc13.setUser(urepo.getById(8));
		sc13.setGrade(95);
		Student_Course sc14=new Student_Course();
		sc14.setCourse(crepo.getById(6));
		sc14.setUser(urepo.getById(9));
		sc14.setGrade(43);
		Student_Course sc15=new Student_Course();
		sc15.setCourse(crepo.getById(6));
		sc15.setUser(urepo.getById(10));
		sc15.setGrade(65);
		
		screpo.save(sc13);screpo.save(sc14); screpo.save(sc15);
		
		//set student for course 5
		Student_Course sc16=new Student_Course();
		sc16.setCourse(crepo.getById(5));
		sc16.setUser(urepo.getById(8));
		sc16.setGrade(95);
		Student_Course sc17=new Student_Course();
		sc17.setCourse(crepo.getById(5));
		sc17.setUser(urepo.getById(9));
		sc17.setGrade(43);
		
		screpo.save(sc16);screpo.save(sc17);
		
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
		
		//add lecturer to course4
		List<User> LectTeach4=new ArrayList<User>();
		LectTeach4.add(urepo.getById(4));
		LectTeach4.add(urepo.getById(5));
		crepo.getById(4).setUser(LectTeach4);
		crepo.save(crepo.getById(4));	
		
		//add lecturer to course5
		List<User> LectTeach5=new ArrayList<User>();
		LectTeach5.add(urepo.getById(6));
		crepo.getById(5).setUser(LectTeach5);
		crepo.save(crepo.getById(5));
		
		//add lecturer to course6
		List<User> LectTeach6=new ArrayList<User>();
		LectTeach6.add(urepo.getById(5));
		crepo.getById(6).setUser(LectTeach6);
		crepo.save(crepo.getById(6));
		
		//delete lecturer to course1
//		List<Course> c=crepo.findallUsersByCourse(1);
//		for(Course i:c)
//			crepo.delete(i);
		
		
	}

}

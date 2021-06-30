package sg.edu.iss.caps.service.implementation;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.DTO.manageCourse.EditCourseDto;
import sg.edu.iss.DTO.manageCourse.EditUserDto;
import sg.edu.iss.caps.model.MailVo;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.repo.StudentCourseRepository;
import sg.edu.iss.caps.repo.UserRepository;
import sg.edu.iss.caps.service.interfaces.ILecturer;
import sg.edu.iss.caps.service.interfaces.IUser;
import sg.edu.iss.caps.utility.UtilityManager;

@Service
public class UserService implements IUser,ILecturer {

	@Autowired UserRepository urepo;
	@Autowired StudentCourseRepository screpo;

	@Autowired JavaMailSenderImpl javaMailSender;

	@Override
	public User findUser(@Valid String email, String password, String identity) {
		// System.out.print(1);
		User user = urepo.findByEmailAndPassword(email, password);
		if(user != null) {

			if (UtilityManager.checkIdentity(user, identity) != "error")
				return user;
		}

		return null;
	}
	
	@Override
	public User findUserById(int id) {
		return urepo.findById(id).orElse(null);
	}

	@Override
	public void sendEmailNotification(MailVo mail) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom(mail.getFrom());
		simpleMailMessage.setTo(mail.getTo());
		simpleMailMessage.setSubject(mail.getSubject());
		simpleMailMessage.setText(mail.getText());
		javaMailSender.send(simpleMailMessage);

	}

	@Transactional
	public User findStudentById(Integer id) {
		 return urepo.findUserByRoleType(id, RoleType.STUDENT);
	}

	@Transactional
	public User findLecturerById(Integer id) {
		 return urepo.findUserByRoleType(id, RoleType.LECTURER);
	}

	@Override
	public List<User> listAll(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<User> listStudents(String keyword) {
		// TODO Auto-generated method stub
		if (keyword != null) {
	        return urepo.searchByRoleType(RoleType.STUDENT, keyword);
	    }
	    return urepo.listAllInRole(RoleType.STUDENT);
	}
	
	@Transactional
	public List<User> listLecturers(String keyword) {
		// TODO Auto-generated method stub
		if (keyword != null) {
	        return urepo.searchByRoleType(RoleType.LECTURER, keyword);
	    }
	    return urepo.listAllInRole(RoleType.LECTURER);
	}
	
	//edit user
	
	  @Transactional
	  public void editUser(EditUserDto editUserDto) throws ParseException {
		User edituser=urepo.findById(editUserDto.getId()).get();
		edituser.setFirstname(editUserDto.getFirstname());
		edituser.setSurname(editUserDto.getSurname());
		edituser.setEmail(editUserDto.getEmail());
		edituser.setImgUrl(editUserDto.getImgUrl());
		edituser.setPassword(editUserDto.getPassword());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  Date enrollmentDate = dateFormat.parse(editUserDto.getEnrollmentDate());
		  long unixEnrollmentDate = (long) enrollmentDate.getTime()/1000;
		  edituser.setEnrollmentDate(unixEnrollmentDate);
		  
		  urepo.save(edituser); 
	  }

	@Transactional
	public void delete(User user) {
		 urepo.delete(user);
	}
	
	//Add new user
	@Transactional
	  public void AddUser(EditUserDto adduserDto) throws ParseException{
		  User newuser = new User();
		  newuser.setFirstname(adduserDto.getFirstname());
		  newuser.setSurname(adduserDto.getSurname());
		  newuser.setEmail(adduserDto.getEmail());
		  newuser.setImgUrl(adduserDto.getImgUrl());
		  newuser.setPassword(adduserDto.getPassword());
		  
		  String role = adduserDto.getRole();
		  RoleType roletype = RoleType.valueOf(role.toUpperCase());
		  newuser.setRole(roletype);
		  
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  Date enrollmentDate = dateFormat.parse(adduserDto.getEnrollmentDate());
		  long unixEnrollmentDate = (long) enrollmentDate.getTime()/1000;
		  newuser.setEnrollmentDate(unixEnrollmentDate);
			
		  urepo.save(newuser);
	  }
	
	@Transactional
	public void adminDeleteStudent(User user){
		List<Student_Course> EnrolledCoursesToDelete=screpo.findStudentCoursesByStudent(user);
		for(Student_Course eachline:EnrolledCoursesToDelete)
			screpo.delete(eachline);
		urepo.delete(user);
	}

	
}

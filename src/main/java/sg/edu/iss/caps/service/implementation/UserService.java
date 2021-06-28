package sg.edu.iss.caps.service.implementation;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.MailVo;
import sg.edu.iss.caps.model.User;
import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.repo.UserRepository;
import sg.edu.iss.caps.service.interfaces.ILecturer;
import sg.edu.iss.caps.service.interfaces.IUser;
import sg.edu.iss.caps.utility.UtilityManager;

@Service
public class UserService implements IUser,ILecturer {

	@Autowired UserRepository urepo;

	@Autowired JavaMailSenderImpl javaMailSender;

	@Override
	public User findUser(@Valid String email, String password, String identity) {

		System.out.print(1);
		User user = urepo.findByEmailAndPassword(email, password);
		if(user != null) {
			System.out.print(2);
			if (UtilityManager.checkIdentity(user, identity) != "error")
				return user;
		}

		return null;
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
	
	public List<User> listStudents(String keyword) {
		// TODO Auto-generated method stub
		if (keyword != null) {
	        return urepo.findStudents(keyword);
	    }
	    return urepo.listAllStudents(RoleType.STUDENT);
	}
	
}

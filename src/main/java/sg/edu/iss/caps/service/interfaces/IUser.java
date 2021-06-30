package sg.edu.iss.caps.service.interfaces;



import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import sg.edu.iss.DTO.manageCourse.EditUserDto;
import sg.edu.iss.caps.model.MailVo;
import sg.edu.iss.caps.model.User;

public interface IUser {

	public User findUserById(int id);
	public User findUser(@Valid String email, String password, String identity);
	public void AddUser(EditUserDto adduserDto) throws ParseException;
	public void editUser(EditUserDto editUserDto) throws ParseException;
	public void adminDeleteStudent(User user);	
	public User findStudentById(Integer id);
	public User findLecturerById(Integer id);
	public List<User> listStudents(String keyword);
	public List<User> listLecturers(String keyword);
	public void delete(User user);
	
	public void sendEmailNotification(MailVo mailVo);
}

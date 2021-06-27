package sg.edu.iss.caps.utility;

import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class UtilityManager {
	  
	public static String checkIdentity(User user, String identity) {
		
		System.out.println(user.getRole()==RoleType.LECTURER);
		System.out.println(identity);
		System.out.println(identity == "lecturer");
		if(user.getRole() == RoleType.STUDENT && identity.equals("student")) {
			return identity;
		} else if (user.getRole() == RoleType.LECTURER && identity.equals("lecturer")) {
			return identity;
		} else if (user.getRole() == RoleType.ADMIN && identity.equals("admin")) {
			return identity;
		} 
		
		return "error";
	}

	public static double GradesToGPA (List<Student_Course> courses) {
		double grades=0;
		double credits=0;
		for (Student_Course course:courses){
			credits+=course.getCourse().getCredit();
			grades+=course.getGrade();
		}
		return credits/grades;
	}

	public static LocalDateTime UnixToDate (long unixTime) {
		return LocalDateTime.ofEpochSecond(unixTime,0, ZoneOffset.ofHours(8));
	}


	public static long DateToUnix (LocalDateTime dateTime){
		return dateTime.toEpochSecond(ZoneOffset.of("+8"));
	}

	//just for test
	public static void main(String[] args) {

	}
 
}
 

package sg.edu.iss.caps.utility;

import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.Student_Course;
import sg.edu.iss.caps.model.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.math3.util.Precision;

public class UtilityManager {
	  
	public static String checkIdentity(User user, String identity) {
		
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
			if(course.getGrade() != 0.0)
				credits+=course.getCourse().getCredit();
			
			grades+=course.getGrade()/100 * course.getCourse().getCredit();
		}
		return Precision.round(grades/credits * 5.0,2);
	}
	
	public static String CalculateCourseDuration (long start, long end) {
		long durationInSeconds = end - start;
		long durationInDays = durationInSeconds/(3600*24);
		return String.valueOf(durationInDays);
	}
	

	public static LocalDateTime UnixToDate (long unixTime) {
		return LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.ofHours(8));
	}
	
	public static String ChangeDateTimeToString(LocalDateTime ldt) {
		String DATE_FORMATTER="dd/MM/yyyy";
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern(DATE_FORMATTER);
		return ldt.format(formatter);
	}


	public static long DateToUnix (LocalDateTime dateTime){
		return dateTime.toEpochSecond(ZoneOffset.of("+8"));
	}

	//just for test
	public static void main(String[] args) {

	}

 
}
 

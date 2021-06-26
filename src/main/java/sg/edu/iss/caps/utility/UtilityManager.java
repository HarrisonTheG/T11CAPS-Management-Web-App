package sg.edu.iss.caps.utility;

import sg.edu.iss.caps.model.RoleType;
import sg.edu.iss.caps.model.User;

public class UtilityManager {
	  
	public static String checkIdentity(User user, String identity) {
		
		if(user.getRole() == RoleType.STUDENT && identity == "student") {
			return identity.toUpperCase();
		} else if (user.getRole() == RoleType.LECTURER && identity == "lecturer") {
			return identity.toUpperCase();
		} else if (user.getRole() == RoleType.ADMIN && identity == "admin") {
			return identity.toUpperCase();
		} 
		
		return "Error";
	}
 
  //public static double gradesToCGPA (List<Student_Course> courses) {
  			
 // }
  
 // public static LocalDateTime UnixToDate (long unixTime) {
	  
  //}
  
 // public static long DateToUnix (LocalDateTime dateTime)
 
}
 

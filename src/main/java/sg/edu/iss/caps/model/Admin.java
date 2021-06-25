package sg.edu.iss.caps.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity @DiscriminatorValue("admin")
public class Admin extends User {

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int id, String firstname, String surname, String email, String password, String imgUrl,
			long enrollmentDate) {
		super(id, firstname, surname, email, password, imgUrl, enrollmentDate);
		// TODO Auto-generated constructor stub
	}

	public Admin(String firstname, String surname, String email, String password, String imgUrl, long enrollmentDate) {
		super(firstname, surname, email, password, imgUrl, enrollmentDate);
		// TODO Auto-generated constructor stub
	}
	
	

}

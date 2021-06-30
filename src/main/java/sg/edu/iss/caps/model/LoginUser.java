package sg.edu.iss.caps.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginUser {
	
	@Email
	private String email;
	@NotBlank
	private String password;
	
	
	public LoginUser(@Email String email, @NotBlank String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public LoginUser() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

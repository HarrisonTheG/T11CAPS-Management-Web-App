package sg.edu.iss.caps.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//this class basically sets up Spring Web Security
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService); //have an instance of the userdetailservice
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests() //authorize all request 
			.antMatchers("/admin").hasAuthority("ADMIN") //allow only admin
			//.antMatchers("/users").hasAnyRole("LECTURER","STUDENT") //allow STUDENT AND LECTURER role
			.antMatchers("/lecturer/*").hasAuthority("LECTURER")//allow only lecturer
			.antMatchers("/student/*").hasAuthority("STUDENT")//allow only student
			.antMatchers("/").permitAll() // / route allow all login
			.and().formLogin().loginPage("/login").permitAll(); //allow form login
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}

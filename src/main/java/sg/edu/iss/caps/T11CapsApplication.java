package sg.edu.iss.caps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//test branch

@SpringBootApplication
public class T11CapsApplication {

	@Autowired
	sg.edu.iss.caps.utility.DbSeeding DbSeeding;
	
	public static void main(String[] args) {
		SpringApplication.run(T11CapsApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner runner() {
		return args->{
			// Seed data only done once
//			DbSeeding.SeedDBWithUsersAndCourses();
//			DbSeeding.SeedDBforCoursesTeachedandEnrolled();
//			
		};
	}
}

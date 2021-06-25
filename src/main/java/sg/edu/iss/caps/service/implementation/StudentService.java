
package sg.edu.iss.caps.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.repo.StudentRepository;
import sg.edu.iss.caps.service.interfaces.IStudent;

@Service
public class StudentService implements IStudent {

	@Autowired
	StudentRepository srepo;

}
 

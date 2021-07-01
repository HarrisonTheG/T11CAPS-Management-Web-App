package sg.edu.iss.caps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.LoginUser;

@Controller
@RequestMapping("")
public class IndexController {

	@GetMapping("")
	public String login(Model model) {
		
		
		return "redirect:/account/login";
	}
	
	@GetMapping("/error")
	public String ErrorPage() {
		return "error";
	}
}

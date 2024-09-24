package bank.app.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@GetMapping("/login/user")
	public String LoginPage() {
		System.out.println("Login called()");
		return "login";
	}
	
	@GetMapping("/register")
	public String openUserRegisterationPage() {
		
		return "user_registration";
	}
}

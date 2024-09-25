package bank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String openHomePage() {
		return "Landingpage";
	}

	@GetMapping("/services")
	public String openServicesPage() {
		return "forward:/services.html";
	}
	
	@GetMapping("/acccounts")
	public String openAccountsPage() {
		return "Landingpage";
	}
	
	@GetMapping("/contact-us")
	public String openContactUsPage() {
		return "contact";
	}
}

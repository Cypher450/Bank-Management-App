package bank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String openHomePage() {
		return "LandingPage";
	}

	@GetMapping("/services")
	public String openServicesPage() {
		return "forward:/Services.html";
	}
	
	@GetMapping("/acccounts")
	public String openAccountsPage() {
		return "LandingPage";
	}
	
	@GetMapping("/contact-us")
	public String openContactUsPage() {
		return "Contact";
	}
}

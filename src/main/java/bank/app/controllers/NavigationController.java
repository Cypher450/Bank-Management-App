package bank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String openHomePage() {
		return "landingPage";
	}

	@GetMapping("/services")
	public String openServicesPage() {
		return "forward:/services.html";
	}

	@GetMapping("/contact-us")
	public String openContactUsPage() {
		return "contact";
	}
}

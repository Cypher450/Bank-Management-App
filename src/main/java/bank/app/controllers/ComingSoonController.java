package bank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComingSoonController {

	@GetMapping("/coming-soon")
	public String openComingSoonPage() {
		return "forward:/comingSoon.html";
	}
}

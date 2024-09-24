package bank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPagesContoller {
	
	
	@GetMapping("/")
    public String homePage() {
        return "forward:/landingpage.html";
    }
	
	 @GetMapping("/contact")
	    public String contactPage() {
	        return "forward:/contact.html";
	    }
	 
	 @GetMapping("/services")
	    public String servicesPage() {
	        return "forward:/services.html";
	    }
}

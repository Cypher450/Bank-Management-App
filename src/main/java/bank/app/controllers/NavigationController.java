package bank.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import bank.app.dao.ContactDaoImpl;
import bank.app.entities.Contact;

@Controller
public class NavigationController {

	@Autowired
	private ContactDaoImpl contactDaoImpl;
	
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
	@GetMapping("/success-page")
	public String openSuccess(@ModelAttribute Contact contact) {
		System.out.println("contact:" +contact);
		int result = contactDaoImpl.insertContact(contact);
		if(result >0) {
			return "forward:/feedback.html";
		}
		return "contact";
	}
}

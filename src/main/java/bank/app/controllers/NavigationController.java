package bank.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/contact-us")
	public String openContactUsPage() {
		return "contact";
	}

	@GetMapping("/success-page")
	public String openSuccess(@ModelAttribute Contact contact, RedirectAttributes attributes) {
		System.out.println("contact:" + contact);
		int result = contactDaoImpl.insertContact(contact);
		if (result > 0) {
			attributes.addFlashAttribute("message", "Your feedback has been noted! We will be contacting you soon.");
			return "redirect:/";
		}

		return "redirect:/contact-us";
	}
}

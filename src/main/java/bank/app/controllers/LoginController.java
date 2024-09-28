package bank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@GetMapping("/openLoginPageCustomer")
	public String openLoginPageCustomer() {
		return "Login";
	}

	@GetMapping("/openLoginPageBankEmp")
	public String openLoginPageBankEmp() {
		return "Login";
	}

	@GetMapping("/openLoginPageBankMgr")
	public String openLoginPageBankMgr() {
		return "Login";
	}

	@GetMapping("/openLoginPageRegionalMgr")
	public String openLoginPageRegionalMgr() {
		return "Login";
	}
}

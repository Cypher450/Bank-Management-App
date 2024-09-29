package bank.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import bank.app.dao.UserDaoImpl;
import bank.app.utility.Password;

@Controller
public class LoginController {

	@Autowired
	UserDaoImpl userDaoImpl;

	@GetMapping("/openLoginPageCustomer")
	public String openLoginPageCustomer() {
		return "login";
	}

	@GetMapping("/openLoginPageBankEmp")
	public String openLoginPageBankEmp() {
		return "login";
	}

	@GetMapping("/openLoginPageBankMgr")
	public String openLoginPageBankMgr() {
		return "login";
	}

	@GetMapping("/openLoginPageRegionalMgr")
	public String openLoginPageRegionalMgr() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {

		System.out.println("\n login request data: " + username + ", " + password);

		Map<String, Object> userData = userDaoImpl.fetchPwds(username);
		String pwdSalt = (String) userData.get("salt_password");
		String oldPwdHash = (String) userData.get("hashed_password");

		System.out.println("pwdSalt :" + pwdSalt);
		System.out.println("oldPwdHash : " + oldPwdHash);

		// Check credentials
		String newPassword = pwdSalt + password;
		String newPasswordHash = Password.generatePwdHash(newPassword);

		if (newPasswordHash.equals(oldPwdHash)) {

			model.addAttribute("userData", userData);

			int roleId = (Integer) userData.get("role_id");
			System.out.println(roleId);
			if (roleId == 1) {
				return "regionalManagerDash";
			} else if (roleId == 2) {
				return "bankManagerDash";
			} else if (roleId == 3) {
				return "bankEmployeeDash";
			} else if (roleId == 4) {
				return "customerDash";
			}
		}

		return "login";
	}
}

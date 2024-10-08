package bank.app.controllers;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bank.app.dao.AccountDaoImpl;
import bank.app.dao.UserDaoImpl;
import bank.app.entities.Account;
import bank.app.entities.Roles;
import bank.app.entities.User;
import bank.app.utility.Password;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	UserDaoImpl userDaoImpl;
	@Autowired
	HttpSession session;
	@Autowired
	AccountDaoImpl accountDaoImpl;

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

	@SuppressWarnings("unused")
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model,
			RedirectAttributes attributes) throws SQLException, IOException {

		User userDetails = userDaoImpl.fetchAllDetails(username).get(0);

		if (userDetails.getRoleId() == 4) {
			List<Account> accountLists = accountDaoImpl.getAccountsByCustomerId(userDetails.getUserId());
			
			if(accountLists.size() == 2) {
				session.setAttribute("accountLists", accountLists);

				Account savingsAcc = null;
				Account currentAcc = null;

				if (accountLists.get(0).getAccountTypeId() == 1) {
					savingsAcc = accountLists.get(0);
					currentAcc = accountLists.get(1);
				} else {
					savingsAcc = accountLists.get(1);
					currentAcc = accountLists.get(0);
				}
				session.setAttribute("currentAcc", currentAcc);
				session.setAttribute("savingsAcc", savingsAcc);
				
			} else if(accountLists.size() == 1){
				
				session.setAttribute("accountLists", accountLists);

				Account savingsAcc = null;
				Account currentAcc = null;

				if (accountLists.get(0).getAccountTypeId() == 1) {
					savingsAcc = accountLists.get(0);
					session.setAttribute("savingsAcc", savingsAcc);
				} else {
					currentAcc = accountLists.get(0);
					session.setAttribute("currentAcc", currentAcc);
				}				
			} else {
				
				Account savingsAcc = null;
				Account currentAcc = null;
				session.setAttribute("currentAcc", currentAcc);
				session.setAttribute("savingsAcc", savingsAcc);
				
				System.out.println("No account found!!");
			}
		}

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
			System.out.println("usersession : " + userDetails);

			if (userDetails != null) {
				session.setAttribute("userDetails", userDetails);
				session.setMaxInactiveInterval(60 * 60);

			} else {
				System.out.println("User details are null");
			}

			model.addAttribute("userData", userData);
			model.addAttribute("userName", username);
			model.addAttribute("userDetails", userDetails);

			Roles role = userDaoImpl.fetchRoleById(userDetails.getRoleId());
			System.out.println("Role: " + role);

			session.setAttribute("role", role);

			int roleId = (Integer) userData.get("role_id");
			if (roleId == 1) {
				attributes.addFlashAttribute("message", "Successfully logged in as Regional Manager.");
				return "regional_mgr/regionalManagerDash";
			} else if (roleId == 2) {
				attributes.addFlashAttribute("message", "Successfully logged in as Bank Manager.");
				return "bank_mgr/bankManagerDash";
			} else if (roleId == 3) {
				attributes.addFlashAttribute("message", "Successfully logged in as Bank Employee.");
				return "bank_emp/bankEmployeeDash";
			} else if (roleId == 4) {
				attributes.addFlashAttribute("message", "Successfully logged in as Customer.");
				return "customer/customerDash";
			}
		} else
			attributes.addFlashAttribute("message", "Password is incorrect.");
		return "login";

	}
}

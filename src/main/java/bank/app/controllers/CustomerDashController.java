package bank.app.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bank.app.dao.AccountDaoImpl;
import bank.app.dao.UserDaoImpl;
import bank.app.entities.Account;
import bank.app.entities.AccountType;
import bank.app.entities.Branch;
import bank.app.entities.Customer;
import bank.app.entities.User;
import bank.app.utility.Password;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("customer")
public class CustomerDashController {

	private User user;
	private Customer customer;
	private Branch branch;
	List<Account> existingAccounts;

	@Autowired
	UserDaoImpl userDaoImpl;

	@Autowired
	AccountDaoImpl accountDaoImpl;

	@Autowired
	private HttpSession session;

	@GetMapping("/dashboard")
	public String customerDashboard() {

		return "customer/customerDash";
	}

	@GetMapping("/edit-profile")
	public String editProfile() {
		return "customer/editProfile";
	}

	@PostMapping("/updateDetails")
	public String updateProfile(@ModelAttribute User updatedUser, RedirectAttributes attributes)
			throws SerialException, IOException, SQLException {

		User userDetails = (User) session.getAttribute("userDetails");
		updatedUser.setUserId(userDetails.getUserId());

		try {
			user = userDaoImpl.modifyUser(updatedUser);
			attributes.addFlashAttribute("message", "Profile updated successfully");
		} catch (EmptyResultDataAccessException e) {
			attributes.addFlashAttribute("message", "Updation failed. Please try again later");
		}

		return "/customer/viewProfile";

	}

	@GetMapping("/change-password")
	public String changePassword() {
		return "customer/changePassword";
	}

	@GetMapping("/view-profile/{username}")
	public ModelAndView viewProfile(@PathVariable String username, ModelAndView modelAndView)
			throws SQLException, IOException {

//		User userDetails = userDaoImpl.fetchAllDetails(username).get(0);

		User userDetails = (User) session.getAttribute("userDetails");

		modelAndView.addObject("userDetails", userDetails);
		modelAndView.setViewName("customer/viewProfile");
		return modelAndView;
	}

	@GetMapping("/openAccountPage")
	public String openAccountPage(Model model) {

		System.out.println("openAccountPage opened");
		try {
			List<AccountType> listOfAccountTypes = userDaoImpl.fetchAllAccountTypes();
			session.setAttribute("listOfAccountTypes", listOfAccountTypes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "customer/openAccount";
	}

	@PostMapping("openAccountSuccess")
	public String openAccountSuccess(@ModelAttribute Account account, RedirectAttributes attributes) {
		User userDetails = (User) session.getAttribute("userDetails");

		try {
			customer = userDaoImpl.fetchCustomerById(userDetails.getUserId());
			branch = userDaoImpl.fetchBranchById(customer.getBranchId());
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		String accountNo = bank.app.utility.Account.accountNoGenerator(branch);
		String ifscCode = bank.app.utility.Account.ifsccodegenerator(branch);

		account.setAccountNumber(accountNo);
		account.setIfscCode(ifscCode);
		account.setCustomerId(customer.getCustomerId());

		try {
			existingAccounts = accountDaoImpl.getAccountsByCustomerId(account.getCustomerId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(existingAccounts.toString());

		boolean hasSavingsAccount = false;
		boolean hasCurrentAccount = false;

		// Check for existing savings and current accounts
		for (Account existingAccount : existingAccounts) {
			if (existingAccount.getAccountTypeId() == 1) { // 1 for savings account
				hasSavingsAccount = true;
			} else if (existingAccount.getAccountTypeId() == 2) { // 2 for current account
				hasCurrentAccount = true;
			}
		}

		// Validate account creation rules
		if (account.getAccountTypeId() == 1 && hasSavingsAccount) {
			attributes.addFlashAttribute("message", "You already have a savings account. You cannot open another.");
			return "customer/openAccount";
		} else if (account.getAccountTypeId() == 2 && hasCurrentAccount) {
			attributes.addFlashAttribute("error", "You already have a current account. You cannot open another.");
			return "customer/openAccount";
		}

		String pwdSalt = (String) userDetails.getPasswordSalt();
		String oldPwdHash = (String) userDetails.getPasswordHashed();
		String password = account.getPassword();

		// Check credentials
		String newPassword = pwdSalt + password;
		String newPasswordHash = Password.generatePwdHash(newPassword);

		if (newPasswordHash.equals(oldPwdHash)) {
			System.out.println("Password is correct.");
			try {
				accountDaoImpl.insertCreatedAccount(account);
				attributes.addFlashAttribute("message", "Account created successfully");
				return "customer/customerDash";
			} catch (SQLException | IOException e) {
				attributes.addFlashAttribute("message", "Something went wrong!");
				return "customer/openAccount";
			}
		} else {
			attributes.addFlashAttribute("message", "Password is incorrect");
			return "customer/openAccount";
		}
	}

}

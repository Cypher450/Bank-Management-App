package bank.app.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import bank.app.dao.UserDaoImpl;
import bank.app.entities.User;
import bank.app.utility.Password;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bank.app.dao.AccountDaoImpl;
import bank.app.dao.TransactionDaoImpl;
import bank.app.entities.Account;
import bank.app.entities.AccountType;
import bank.app.entities.Branch;
import bank.app.entities.Customer;
import bank.app.entities.Transaction;

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
	TransactionDaoImpl transactionDaoImpl;

	@Autowired
	AccountDaoImpl accountDaoImpl;

	@Autowired
	private HttpSession session;

	@GetMapping("/dashboard")
	public String customerDashboard(HttpSession session) {
	    return "customer/customerDash";
	}

	
	@GetMapping("/select-account")
	public String selectAccount() {
		return "customer/selectAccount";
	}
	
	@GetMapping("/select-statement-account")
	public String selectStmtAccount() {
		return "customer/selectAccountAccStmt";
	}

	@GetMapping("/edit-profile")
	public String editProfile() {
		return "editProfile";
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
		
		session.setAttribute("userDetails", user);

		return "viewProfile";

	}

	@GetMapping("/change-password")
	public String openChangePassword() {
		return "changePassword";
	}

	@PostMapping("/change-password")
	public String changePassword(@RequestParam String newPassword, @RequestParam String currentPassword, Model model,
			RedirectAttributes attributes) throws SerialException, IOException, SQLException {

		User user = (User) session.getAttribute("userDetails");

		Map<String, Object> userData = userDaoImpl.fetchPwds(user.getUsername());

		String pwdSalt = (String) userData.get("salt_password");
		String dbPwdHash = (String) userData.get("hashed_password");

		// Check credentials
		String newPass = pwdSalt + currentPassword;
		String currentPassHash = Password.generatePwdHash(newPass);

		if (currentPassHash.equals(dbPwdHash)) {
			System.out.println("equal");

			String newPassHash = Password.generatePwdHash(pwdSalt + newPassword);

			userDaoImpl.updatePassword(newPassHash, user);
			attributes.addFlashAttribute("message", "Password changed successfully!");

		} else {
			System.out.println("Old Password doesnt match!!");
			attributes.addFlashAttribute("message", "Current password is not correct!");
			return "changePassword";
		}

		return "landingPage";
	}

	@GetMapping("/view-profile/{username}")
	public ModelAndView viewProfile(@PathVariable String username, ModelAndView modelAndView)
			throws SQLException, IOException {
		System.out.println("Session ID: " + session.getId());
		System.out.println("Session Attributes: " + Collections.list(session.getAttributeNames()));

//		User userDetails = userDaoImpl.fetchAllDetails(username).get(0);

		User userDetails = (User) session.getAttribute("userDetails");
		System.out.println("view : " + userDetails);

		modelAndView.addObject("userDetails", userDetails);
		modelAndView.setViewName("viewProfile");
		return modelAndView;
	}

	@GetMapping("/openAccountPage")
	public String openAccountPage(Model model, RedirectAttributes attributes) {

		System.out.println("openAccountPage opened");
		try {
			List<AccountType> listOfAccountTypes = userDaoImpl.fetchAllAccountTypes();
			session.setAttribute("listOfAccountTypes", listOfAccountTypes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			attributes.addFlashAttribute("message", "Something went wrong in fetching list of account types.");
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
			attributes.addFlashAttribute("message", "You already have a current account. You cannot open another.");
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
				
				List<Account> accountLists = accountDaoImpl.getAccountsByCustomerId(userDetails.getUserId());
				System.out.println("account list : " + accountLists);
				session.setAttribute("accountLists", accountLists);
				
				if(account.getAccountTypeId() == 2) {
					session.setAttribute("currentAcc", account);
				} else {
					session.setAttribute("savingsAcc", account);
				}
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
	
	@GetMapping("/account-details")
	public String accountDetails() {
		return "customer/accountDetails";
	}
	
	
	@GetMapping("/full-account-details/{accountType}")
	public ModelAndView fullSavingsAccDetails(@PathVariable String accountType,ModelAndView modelAndView) throws SQLException, IOException {
		if(accountType.equals("savings")) {
			Account accountDetailsSavings = (Account)session.getAttribute("savingsAcc"); 
			session.setAttribute("accountDetails", accountDetailsSavings);
		} else {
			Account accountDetailsCurrent = (Account)session.getAttribute("currentAcc"); 
			session.setAttribute("accountDetails", accountDetailsCurrent);
		}
			
		modelAndView.setViewName("customer/fullAccountDetails");
		
		return modelAndView;
	}
	
	@GetMapping("/account-Statement")
	public String showStatement(@RequestParam String accountNo, Model model) throws SQLException, IOException {

		List<Transaction> transactions = transactionDaoImpl.getTransaction(accountNo);
		Double totalDebits = transactionDaoImpl.totalDebits(accountNo);
		Double totalCredits = transactionDaoImpl.totalCredits(accountNo);
		
		model.addAttribute("totalCredits",totalCredits);
		model.addAttribute("totalDebits",totalDebits);
		
		@SuppressWarnings("unused")
		Account accountDetailsSavings = (Account) session.getAttribute("savingsAcc");
		Account accountDetailsCurrent = (Account) session.getAttribute("currentAcc");
		
		System.out.println("savings : " + accountDetailsSavings.getAccountNumber());

		if (accountDetailsSavings.getAccountNumber().equals(accountNo)) {
			model.addAttribute("accountDetails", accountDetailsSavings);
		} else {
			model.addAttribute("accountDetails", accountDetailsCurrent);
		}

		model.addAttribute("transactions", transactions);

		return "/customer/accountStatement";

	}

}

package bank.app.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bank.app.dao.AccountDaoImpl;
import bank.app.dao.BankDaoImpl;
import bank.app.dao.UserDaoImpl;
import bank.app.entities.Account;
import bank.app.entities.Branch;
import bank.app.entities.Customer;
import bank.app.entities.User;
import bank.app.utility.Password;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("bank_emp")
public class BankEmpDashController {

	private Customer customer;
	private Branch branch;

	@Autowired
	UserDaoImpl userDaoImpl;

	@Autowired
	BankDaoImpl bankDaoImpl;

	@Autowired
	AccountDaoImpl accountDaoImpl;

	@Autowired
	private HttpSession session;

	int branchId;
	List<User> customers;
	List<Account> accountList;

	@GetMapping("/dashboard")
	public String BankEmployeeDashboard() {

		return "bank_emp/bankEmployeeDash";
	}

	@GetMapping("/view-profile/{username}")
	public ModelAndView viewProfile(@PathVariable String username, ModelAndView modelAndView)
			throws SQLException, IOException {

		User userDetails = (User) session.getAttribute("userDetails");

		modelAndView.addObject("userDetails", userDetails);
		modelAndView.setViewName("viewProfile");
		return modelAndView;
	}

	@GetMapping("/edit-profile")
	public String editProfile() {
		return "editProfile";
	}

	@GetMapping("/approval-list")
	public String showApprovalList() {

		User userDetails = (User) session.getAttribute("userDetails");
		int empUserId = userDetails.getUserId();

		try {
			branchId = bankDaoImpl.getBranchIdByEmpId(empUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Fetch customers associated with the same branch
		try {
			customers = userDaoImpl.getCustomersApprovalList(branchId);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.setAttribute("customerApprovalList", customers);

		return "bank_emp/customerApprovalList";
	}

	@PostMapping("/customerApprove/{userId}")
	public String customerApprove(@PathVariable int userId, RedirectAttributes attributes) {

		userDaoImpl.changeApprovalStatus(userId);

		attributes.addFlashAttribute("message", "Customer Approved!");

		return "redirect:/bank_emp/approval-list";
	}

	@PostMapping("/customerReject/{userId}")
	public String customerReject(@PathVariable int userId, RedirectAttributes attributes) {

		userDaoImpl.changeApprovalStatusReject(userId);

		attributes.addFlashAttribute("message", "Customer Rejected!");

		return "redirect:/bank_emp/approval-list";
	}

	@GetMapping("/open-acc-pending-list")
	public String customerAccPendingList() {

		User userDetails = (User) session.getAttribute("userDetails");
		int empUserId = userDetails.getUserId();

		try {
			branchId = bankDaoImpl.getBranchIdByEmpId(empUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		// Fetch customers associated with the same branch
		try {
			customers = userDaoImpl.getCustomerPendingAccount(branchId);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.setAttribute("savingsAccountOpeningList", customers);
		return "bank_emp/openAccPendList";
	}

	@GetMapping("/employee-open-savings-account-form/{userId}")
	public String empOpenSavingsAcc(@PathVariable int userId, Model model) {

		model.addAttribute("SavingsAccountOpeningUserId", userId);

		return "bank_emp/custOpenAccountSavings";
	}

	@PostMapping("/accountOpenSuccess/{custUserId}")
	public String accountOpenSuccess(@PathVariable int custUserId, @ModelAttribute Account account,
			RedirectAttributes attributes) {

		User userDetails = (User) session.getAttribute("userDetails");

		try {
			customer = userDaoImpl.fetchCustomerById(custUserId);
			branch = userDaoImpl.fetchBranchById(customer.getBranchId());
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		String accountNo = bank.app.utility.Account.accountNoGenerator(branch);
		String ifscCode = bank.app.utility.Account.ifsccodegenerator(branch);

		account.setAccountNumber(accountNo);
		account.setIfscCode(ifscCode);
		account.setCustomerId(customer.getCustomerId());

		String pwdSalt = (String) userDetails.getPasswordSalt();
		String oldPwdHash = (String) userDetails.getPasswordHashed();
		String password = account.getPassword();

		// Check credentials
		String newPassword = pwdSalt + password;
		String newPasswordHash = Password.generatePwdHash(newPassword);

		if (newPasswordHash.equals(oldPwdHash)) {
			try {
				accountDaoImpl.insertCreatedAccount(account);

				attributes.addFlashAttribute("message", "Account created successfully");
				return "redirect:/bank_emp/open-acc-pending-list";
			} catch (SQLException | IOException e) {
				attributes.addFlashAttribute("message", "Something went wrong!");
				return "redirect:/bank_emp/open-acc-pending-list";
			}
		} else {
			attributes.addFlashAttribute("message", "Password is incorrect");
			return "redirect:/bank_emp/employee-open-savings-account-form/" + custUserId;
		}
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

			String newPassHash = Password.generatePwdHash(pwdSalt + newPassword);

			userDaoImpl.updatePassword(newPassHash, user);
			attributes.addFlashAttribute("message", "Password changed successfully! Please login again");
			return "redirect:/";

		} else {
			attributes.addFlashAttribute("message", "Current password is not correct!");
			return "redirect:/bank_emp/change-password";
		}
	}

	@GetMapping("/manageCustomers")
	public String manageCustomers() {
		// Get bank employee details from session
		User userDetails = (User) session.getAttribute("userDetails");
		int empUserId = userDetails.getUserId();

		// Get branch_id associated with the bank employee
		try {
			branchId = bankDaoImpl.getBranchIdByEmpId(empUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Fetch customers associated with the same branch
		try {
			customers = userDaoImpl.getCustomersByBranch(branchId);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.setAttribute("customers", customers);

		return "bank_emp/manageCustomers";
	}

	@GetMapping("/editCustomer/{userId}")
	public String editCustomer(@PathVariable int userId) {
		User customer = userDaoImpl.getUserById(userId);
		session.setAttribute("customer", customer);
		return "bank_emp/editCustomer";
	}

	@PostMapping("/updateCustomer")
	public String updateCustomer(@ModelAttribute User customer, RedirectAttributes attributes) {
		try {
			userDaoImpl.updateCustomer(customer);
			attributes.addFlashAttribute("message", "Customer details updated successfully!");
			return "redirect:/bank_emp/manageCustomers";
		} catch (SerialException e) {
			attributes.addFlashAttribute("message", "Something went wrong!");
			return "redirect:/bank_emp/editCustomer/" + customer.getUserId();
		} catch (IOException e) {
			return "redirect:/bank_emp/editCustomer/" + customer.getUserId();
		} catch (SQLException e) {
			return "redirect:/bank_emp/editCustomer/" + customer.getUserId();
		}

	}

	@PostMapping("/deleteCustomer")
	@ResponseBody
	public String deleteCustomer(@RequestParam int userId) {

		try {
			accountList = accountDaoImpl.getAccountsByCustomerId(userId);
			userDaoImpl.softDeleteCustomer(userId, accountList);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}

	@GetMapping("/viewCustomerInfo/{userId}")
	public String viewCustomerInfo(@PathVariable int userId) {
		User customer = userDaoImpl.getUserById(userId);

		session.setAttribute("customer", customer);

		return "bank_emp/viewCustomerInfo";
	}
}

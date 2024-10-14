package bank.app.controllers;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bank.app.dao.AccountDaoImpl;
import bank.app.dao.BankDaoImpl;
import bank.app.dao.UserDaoImpl;
import bank.app.entities.Account;
import bank.app.entities.Branch;
import bank.app.entities.Roles;
import bank.app.entities.User;
import bank.app.utility.Password;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	private UserDaoImpl userDaoImpl;
	@Autowired
	private HttpSession session;
	@Autowired
	private AccountDaoImpl accountDaoImpl;

	@Autowired
	private BankDaoImpl bankDaoImpl;

	private User userDetails;
	private int branchId;
	private List<User> customers;
	private List<User> employees;
	private int regionId;
	private List<Branch> branches;
	private List<User> branchManagers;

	@GetMapping("/openLoginPageCustomer")
	public String openLoginPageCustomer(Model model) {
		model.addAttribute("roleId", 4);
		return "login";
	}

	@GetMapping("/openLoginPageBankEmp")
	public String openLoginPageBankEmp(Model model) {
		model.addAttribute("roleId", 3);
		return "login";
	}

	@GetMapping("/openLoginPageBankMgr")
	public String openLoginPageBankMgr(Model model) {
		model.addAttribute("roleId", 2);
		return "login";
	}

	@GetMapping("/openLoginPageRegionalMgr")
	public String openLoginPageRegionalMgr(Model model) {
		model.addAttribute("roleId", 1);
		return "login";
	}

	@SuppressWarnings("unused")
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, @RequestParam int roleId,
			RedirectAttributes attributes, Model model) throws SQLException, IOException {

		// Your authentication logic here
		boolean isAuthenticated = authenticateUser(username, roleId);

		if (isAuthenticated) {
			userDetails = userDaoImpl.fetchAllDetails(username).get(0);
			if (roleId == 3) {
				int empUserId = userDetails.getUserId();
				branchId = bankDaoImpl.getBranchIdByEmpId(empUserId);
				customers = userDaoImpl.getCustomersByBranch(branchId);
				session.setAttribute("customers", customers);
			} else if (roleId == 2) {
				int mgrUserId = userDetails.getUserId();
				branchId = bankDaoImpl.getbranchIdByMgrId(mgrUserId);
				customers = userDaoImpl.getCustomersByBranch(branchId);
				session.setAttribute("customers", customers);
				employees = userDaoImpl.getEmployeesByBranch(branchId);
				session.setAttribute("employees", employees);
			} else if (roleId == 1) {
				int regionalMgrUserId = userDetails.getUserId();
				regionId = bankDaoImpl.getRegionIdByMgrId(regionalMgrUserId);
				branches = bankDaoImpl.getBranchesByRegionId(regionId);
				session.setAttribute("branches", branches);
				branchManagers = userDaoImpl.getBranchManagersForRegion(branches);
				session.setAttribute("branchManagers", branchManagers);

				List<User> allCustomers = new ArrayList<>();
				List<User> allEmployees = new ArrayList<>();

				// Loop through each branch and get customers and employees by branchId
				for (Branch branch : branches) {
					int branchId = branch.getBranchId();

					// Get customers for the current branch
					List<User> filteredCustomers = userDaoImpl.getCustomersByBranch(branchId);
					allCustomers.addAll(filteredCustomers); // Add filtered customers to allCustomers list

					// Get employees for the current branch
					List<User> filteredEmployees = userDaoImpl.getEmployeesByBranch(branchId);
					allEmployees.addAll(filteredEmployees); // Add filtered employees to allEmployees list
				}

				System.out.println("Total employees: " + allEmployees);
				System.out.println("Total customers: " + allCustomers);

				// Set the consolidated lists in the session
				session.setAttribute("customers", allCustomers);
				session.setAttribute("employees", allEmployees);
			}

			if (userDetails.getRoleId() == 4) {
				List<Account> accountLists = accountDaoImpl.getAccountsByCustomerId(userDetails.getUserId());
				if (accountLists.size() == 2) {
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
				} else if (accountLists.size() == 1) {
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
				}
			}

			Map<String, Object> userData = userDaoImpl.fetchPwds(username);
			String pwdSalt = (String) userData.get("salt_password");
			String oldPwdHash = (String) userData.get("hashed_password");

			// Check credentials
			String newPassword = pwdSalt + password;
			String newPasswordHash = Password.generatePwdHash(newPassword);

			if (newPasswordHash.equals(oldPwdHash)) {
				if (userDetails != null) {
					session.setAttribute("userDetails", userDetails);
				}

				model.addAttribute("userData", userData);
				model.addAttribute("userName", username);
				model.addAttribute("userDetails", userDetails);

				Roles role = userDaoImpl.fetchRoleById(userDetails.getRoleId());

				session.setAttribute("role", role);

				if (userDetails.getApprovalStatus().equals("approved")
						&& userDetails.getActiveStatus().equals("true")) {
					switch (roleId) {
					case 4:
						return "redirect:/customer/dashboard";
					case 3:
						return "redirect:/bank_emp/dashboard";
					case 2:
						return "redirect:/bank_mgr/dashboard";
					case 1:
						return "redirect:/regional_mgr/dashboard";
					}
				} else {
					attributes.addFlashAttribute("message", "You have not been approved yet, please try again later!");
					return getRedirectUrlByRoleId(roleId);
				}
			} else {
				attributes.addFlashAttribute("message", "Invalid Password");
				return getRedirectUrlByRoleId(roleId);
			}

		} else {
			attributes.addFlashAttribute("message", "Invalid username");
			return getRedirectUrlByRoleId(roleId);
		}

		return "landingPage";
	}

	private boolean authenticateUser(String username, int roleId) {
		String sql = "SELECT COUNT(*) FROM user WHERE username = ? AND role_id = ?";
		@SuppressWarnings("deprecation")
		Integer count = jdbcTemplate.queryForObject(sql, new Object[] { username, roleId }, Integer.class);
		return count != null && count > 0;
	}

	private String getRedirectUrlByRoleId(int roleId) {
		switch (roleId) {
		case 1:
			return "redirect:/openLoginPageRegionalMgr";
		case 2:
			return "redirect:/openLoginPageBankMgr";
		case 3:
			return "redirect:/openLoginPageBankEmp";
		case 4:
			return "redirect:/openLoginPageCustomer";
		default:
			return "redirect:/";
		}
	}
}

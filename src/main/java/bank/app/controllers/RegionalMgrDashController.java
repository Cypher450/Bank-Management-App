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

import bank.app.dao.BankDaoImpl;
import bank.app.dao.UserDaoImpl;
import bank.app.entities.Branch;
import bank.app.entities.User;
import bank.app.utility.Password;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("regional_mgr")
public class RegionalMgrDashController {
	@Autowired
	UserDaoImpl userDaoImpl;

	@Autowired
	private HttpSession session;

	@Autowired
	private BankDaoImpl bankDaoImpl;

	int regionId;
	List<Branch> branches;
	List<User> branchManagers;

	@GetMapping("/dashboard")
	public String BankManagerDashboard() {

		return "regional_mgr/regionalManagerDash";
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
			return "redirect:/customer/change-password";
		}
	}

	@GetMapping("manageBranchManagers")
	public String manageCustomers() {
		// Get regional manager details from session
		User userDetails = (User) session.getAttribute("userDetails");
		int regionalMgrUserId = userDetails.getUserId();

		// Get region_id associated with the regional manager
		try {
			regionId = bankDaoImpl.getRegionIdByMgrId(regionalMgrUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Fetch List<Branch> branches associated with the same region
		try {
			branches = bankDaoImpl.getBranchesByRegionId(regionId);
			branchManagers = userDaoImpl.getBranchManagersForRegion(branches);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.setAttribute("branchManagers", branchManagers);

		return "regional_mgr/manageBranchManagers";
	}

	@GetMapping("/editBankManager/{userId}")
	public String editBankManager(@PathVariable int userId) {
		User bankManager = userDaoImpl.getUserById(userId);
		session.setAttribute("bankManager", bankManager);
		return "regional_mgr/editBankManager";
	}

	@PostMapping("/updateBankManager")
	public String updateBankManager(@ModelAttribute User bankManager, RedirectAttributes attributes) {
		try {
			userDaoImpl.updateBankManager(bankManager);
			attributes.addFlashAttribute("message", "Customer details updated successfully!");
			return "redirect:/regional_mgr/manageBranchManagers";
		} catch (SerialException e) {
			attributes.addFlashAttribute("message", "Something went wrong!");
			return "redirect:/regional_mgr/editBankManager/" + bankManager.getUserId();
		} catch (IOException e) {
			return "redirect:/regional_mgr/editBankManager/" + bankManager.getUserId();
		} catch (SQLException e) {
			return "redirect:/regional_mgr/editBankManager/" + bankManager.getUserId();
		}
	}

	@PostMapping("/deleteBankManager")
	@ResponseBody
	public String deleteBankManager(@RequestParam int userId) {
		try {
			userDaoImpl.softDeleteBankManager(userId);
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}

	@GetMapping("/viewBankManagerInfo/{userId}")
	public String viewBankManagerInfo(@PathVariable int userId) {
		User bankManager = userDaoImpl.getUserById(userId);

		session.setAttribute("bankManager", bankManager);

		return "regional_mgr/viewBankManagerInfo";
	}

}

package bank.app.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bank.app.dao.UserDaoImpl;
import bank.app.entities.User;
import bank.app.utility.Password;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("bank_mgr")
public class BankMgrDashController {

	@Autowired
	UserDaoImpl userDaoImpl;

	@Autowired
	private HttpSession session;

	@GetMapping("/dashboard")
	public String BankManagerDashboard() {

		return "bank_mgr/bankManagerDash";
	}

	@GetMapping("/view-profile/{username}")
	public ModelAndView viewProfile(@PathVariable String username, ModelAndView modelAndView)
			throws SQLException, IOException {

//		User userDetails = userDaoImpl.fetchAllDetails(username).get(0);

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

}

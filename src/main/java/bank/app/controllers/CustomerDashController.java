package bank.app.controllers;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import bank.app.dao.UserDaoImpl;
import bank.app.entities.User;
import bank.app.utility.Password;
import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("customer")
public class CustomerDashController {

	private User user;

	@Autowired
	UserDaoImpl userDaoImpl;
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
		
		System.out.println("userDetails in updateDetails controller :"+userDetails);
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
	public String openChangePassword() {
		return "customer/changePassword";
	}
	
	@PostMapping("/change-password")
	public String changePassword(@RequestParam String newPassword, @RequestParam String currentPassword, Model model) throws SerialException, IOException, SQLException {
		
		User user = (User)session.getAttribute("userDetails");
		
		Map<String, Object> userData = userDaoImpl.fetchPwds(user.getUsername());
		
		String pwdSalt = (String) userData.get("salt_password");
		String dbPwdHash = (String) userData.get("hashed_password");
		
		// Check credentials
				String newPass = pwdSalt + currentPassword;
				String currentPassHash = Password.generatePwdHash(newPass);
		
				if(currentPassHash.equals(dbPwdHash)) {
					System.out.println("equal");
					
					String newPassHash = Password.generatePwdHash(pwdSalt + newPassword);
					
					userDaoImpl.updatePassword(newPassHash, user);
					
				} else {
					System.out.println("Old Password doesnt match!!");
				}
				
				return "landingPage";
	}

	@GetMapping("/view-profile/{username}")
	public ModelAndView viewProfile(@PathVariable String username, ModelAndView modelAndView)
			throws SQLException, IOException {

//		User userDetails = userDaoImpl.fetchAllDetails(username).get(0);

		User userDetails = (User) session.getAttribute("userDetails");

		modelAndView.addObject("userDetails", userDetails);

		System.out.println("user details : " + userDetails);
		modelAndView.setViewName("customer/viewProfile");
		return modelAndView;
	}

}

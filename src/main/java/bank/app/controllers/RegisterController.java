package bank.app.controllers;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import bank.app.dao.UserDaoImpl;
import bank.app.entities.Branch;
import bank.app.entities.Roles;
import bank.app.entities.User;
import bank.app.utility.Password;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

	@Autowired
	UserDaoImpl userDaoImpl;

	@GetMapping("/userRegistration")
	public ModelAndView userRegistration(ModelAndView modelAndView) {

		List<Roles> listOfRoles = userDaoImpl.fetchAllRoles();
		List<Branch> listOfBranch = userDaoImpl.fetchAllBranch();

		System.out.println("userRegistration invoked");
		System.out.println("Roles : " + listOfRoles);

		modelAndView.addObject("listOfRoles", listOfRoles);
		modelAndView.addObject("listOfBranch", listOfBranch);
		modelAndView.setViewName("userRegistration");

		return modelAndView;
	}

	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model, RedirectAttributes attributes) throws SQLException, IOException {

		System.out.println(user);

		String passwordSalt = Password.generatePwdSalt(10);
		user.setPasswordSalt(passwordSalt);

		String newPassword = passwordSalt + user.getPassword();

		String passwordHash = Password.generatePwdHash(newPassword);
		user.setPasswordHashed(passwordHash);
		user.setApprovalStatus("pending");

		int userId = userDaoImpl.insertUser(user);

		// Based on the role, insert into the appropriate table (Bank Manager, Employee,
		// Customer)
		switch (user.getRoleId()) {
		case 2:
			userDaoImpl.insertBankManager(userId, user.getBranchId());
			break;
		case 3:
			userDaoImpl.insertEmployee(userId, user.getBranchId());
			break;
		case 4:
			userDaoImpl.insertCustomer(userId, user.getBranchId());
			break;
		default:
			throw new IllegalArgumentException("Invalid role: " + user.getRoleId());
		}

		if (userId > 0) {
			System.out.println("passed insertion");
			attributes.addFlashAttribute("message", "Registration successful. You can now login after the approval.");
			return "redirect:/";
		} else {

			System.out.println("failed insertion");
			attributes.addFlashAttribute("message", "Something went wrong! Please try again.");
			return "redirect:/userRegistration";
		}
	}

}

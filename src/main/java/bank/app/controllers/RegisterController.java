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
		modelAndView.addObject("listOfBranch",listOfBranch);
		modelAndView.setViewName("UserRegistration");
		
		return modelAndView;
	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model ) throws SQLException, IOException {

		
		System.out.println(user);
		
		String passwordSalt = Password.generatePwdSalt(10);
		user.setPasswordSalt(passwordSalt);
		
		String newPassword = passwordSalt + user.getPassword();
		
		
		String passwordHash = Password.generatePwdHash(newPassword);
		user.setPasswordHashed(passwordHash);
		
		
		
		
		int result = userDaoImpl.insertUser(user);
		
		
		if(result > 0) {
			System.out.println("passed insertion");
			return "LandingPage";
		} else {
			
			System.out.println("failed insertion");
			return "UserRegistration";
		}
	}

}

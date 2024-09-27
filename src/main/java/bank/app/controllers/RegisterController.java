package bank.app.controllers;

import java.io.IOException;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import bank.app.dao.UserDaoImpl;
import bank.app.entities.User;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class RegisterController {
	
	@Autowired
	UserDaoImpl userDaoImpl;

	@GetMapping("/userRegistration")
	public String getMethodNa() {
		
		
		return "user_registration";
	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model ) throws SQLException, IOException {

		
		int result = userDaoImpl.insertUser(user);
		
		
		if(result > 0) {
			System.out.println("passed insertion");
			return "redirect:/Landingpage";
		} else {
			
			System.out.println("failed insertion");
			return "redirect:/user_registration";
		}
	}
	
	
}

package bank.app.controllers;

import java.io.IOException;


import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import bank.app.dao.UserDaoImpl;
import bank.app.entities.User;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerDashController {
	
	private User user;

	@Autowired
	UserDaoImpl userDaoImpl;
	@Autowired
	private HttpSession session;

	@GetMapping("/customerDashboard")
	public String customerDashboard() {

		return "customerDash";
	}

	@GetMapping("/edit-profile")
	public String editProfile() {
		return "editProfile";
	}
	
	@PostMapping("/updateDetails")
	public String updateProfile(@ModelAttribute User updatedUser, RedirectAttributes attributes) throws SerialException, IOException, SQLException {
		
		  System.out.println(updatedUser);
		try {
			user = userDaoImpl.modifyUser(updatedUser);
				System.out.println("Success : "+ user);
				attributes.addFlashAttribute("message", "Profile updated successfully");			
		} catch(EmptyResultDataAccessException e) {
			attributes.addFlashAttribute("message", "Updation failed. Please try again later");
		}
		return "viewProfile";
		
		
	}

	@GetMapping("/change-password")
	public String changePassword() {
		return "changePassword";
	}
	
	@GetMapping("/view-profile/{username}")
	public ModelAndView viewProfile(@PathVariable String username, ModelAndView modelAndView) throws SQLException, IOException {

//		User userDetails = userDaoImpl.fetchAllDetails(username).get(0);
		
		User userDetails = (User)session.getAttribute("userDetails");
		

		modelAndView.addObject("userDetails",userDetails);
		
		System.out.println("user details : " + userDetails);
		modelAndView.setViewName("viewProfile");
		return modelAndView;
	}



}

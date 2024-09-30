package bank.app.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bank.app.dao.UserDaoImpl;
import bank.app.entities.User;
import ch.qos.logback.core.model.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerDashController {

	@Autowired
	UserDaoImpl userDaoImpl;

	@GetMapping("/customerDashboard")
	public String customerDashboard() {

		return "customerDash";
	}

	@GetMapping("/edit-profile")
	public String editProfile() {
		return "editProfile";
	}

	@GetMapping("/view-profile/{username}")
	public ModelAndView viewProfile(@PathVariable String username, ModelAndView modelAndView) throws SQLException, IOException {

		User userDetails = userDaoImpl.fetchAllDetails(username).get(0);

		modelAndView.addObject("userDetails",userDetails);
		modelAndView.setViewName("viewProfile");
		return modelAndView;
	}



}

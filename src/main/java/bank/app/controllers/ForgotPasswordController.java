package bank.app.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bank.app.dao.UserDaoImpl;
import bank.app.service.EmailService;
import bank.app.utility.Password;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class ForgotPasswordController {
	@Autowired
	private EmailService emailService;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserDaoImpl userDaoImpl;

	@GetMapping("/send-email")
	public String sendEmail(@RequestParam String to, HttpSession session, RedirectAttributes attributes) {
		String otp = String.valueOf((int) ((Math.random() * 900000) + 100000)); // Generate 6-digit OTP
		System.out.println("otp for email " + to + " is: " + otp);

		session.setAttribute("otp", otp);
		session.setAttribute("email", to);

		try {
			emailService.sendEmail(to, otp);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		attributes.addFlashAttribute("message", "OTP sent to your email.");
		return "redirect:/auth/verify-otp";
	}

	@GetMapping("/forgot-password")
	public String showForgotPasswordPage() {
		return "forgotPassword";
	}
	
	@GetMapping("/verify-otp")
	public String openVerifyOtpPage() {
		return "otpVerification";
	}

	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam String otp, HttpSession session, RedirectAttributes attributes) {
		String sessionOtp = (String) session.getAttribute("otp");
		if (sessionOtp == null || !(sessionOtp.equals(otp))) {
			attributes.addFlashAttribute("message", "Invalid OTP.");
			return "redirect:/auth/verify-otp";
		}
		session.removeAttribute("otp");
		return "redirect:/auth/reset-password";
	}
	
	@GetMapping("/reset-password")
	public String resetPasswordPage() {
		return "resetPassword";
	}

	@PostMapping("/reset-password")
	public String resetPassword(@RequestParam String newPassword, HttpSession session, RedirectAttributes attributes) {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			attributes.addFlashAttribute("message", "Invalid session.");
			return "redirect:/auth/forgot-password";
		}

		// Password reset logic here (e.g., update in DB)
		System.out.println("Password updated for email: " + email);

		String passwordSalt = Password.generatePwdSalt(10);
		String password = passwordSalt + newPassword;
		String passwordHashed = Password.generatePwdHash(password);

		String sql = "UPDATE user SET salt_password = ?, hashed_password = ? WHERE email = ?";
		jdbcTemplate.update(sql, passwordSalt, passwordHashed, email);

		session.invalidate();
		attributes.addFlashAttribute("message", "Password reset successfully.");
		return "redirect:/";
	}
	
	@GetMapping("/checkEmail")
	@ResponseBody
	public String checkUsername(@RequestParam("email") String email, @RequestParam("roleId") int roleId) {
		boolean exists = false;
		try {
			exists = userDaoImpl.emailExists(email, roleId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return exists ? "exists" : "available";
	}
}

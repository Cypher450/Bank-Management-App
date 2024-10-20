package bank.app.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;

	public void sendEmail(String to, String otp) throws MessagingException {
		// Create a MimeMessage for HTML content
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		// Construct the HTML message with the OTP
		String emailContent = "Your OTP Code is : " + otp;

		String fromEmail = env.getProperty("spring.mail.username");

		// Set email parameters
		helper.setTo(to);
		helper.setSubject("Password Reset OTP");
		helper.setText(emailContent);
		helper.setFrom(fromEmail);

		// Send the email
		mailSender.send(mimeMessage);
	}
}

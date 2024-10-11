<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Contact Us - XYZ Bank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/contact.css">
</head>
<body>
	<header>
		<h1>Contact Us</h1>
	</header>

	<section>
		<h2>We'd Love to Hear From You!</h2>
		<div class="contact-info">
			<p>
				Phone: <a href="tel:+91 9001967580">+91 9001967589</a>
			</p>
			<p>
				Email: <a href="mailto:support@xyzbank.com">support@xyzbank.com</a>
			</p>
			<p>Address: Sector 59 Noida India</p>
		</div>

		<form action="/success-page">
			<input type="text" name="full_name" placeholder="Your Name" required>
			<input type="email" name="email" placeholder="Your Email" required>
			<textarea name="message" placeholder="Your Message" required></textarea>
			<button type="submit" class="btn">Send Message</button>
		</form>
	</section>
	<%@include file="message.jsp"%>

	<footer>
		<p>&copy; 2024 XYZ Bank. All rights reserved.</p>
	</footer>

</body>
</html>

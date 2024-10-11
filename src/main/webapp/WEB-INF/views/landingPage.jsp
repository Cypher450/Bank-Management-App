<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Bank Application - Welcome</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/landingPage.css">

</head>
<body>
	<header>
		<h1>Welcome to XYZ Bank</h1>
		<p>Your trusted partner for secure banking services</p>
	</header>

	<nav>
		<a href="/">Home</a> <a href="/services">Services</a> <a
			href="/coming-soon">Accounts</a> <a href="/contact-us">Contact Us</a>
	</nav>

	<section>
		<h2>Banking made easy for everyone</h2>
		<p>Manage your accounts, transactions, and banking needs securely
			from anywhere.</p>

		<div class="cta">
			<a href="/openLoginPageCustomer" class="btn">Login as Customer</a> <a
				href="/openLoginPageBankEmp" class="btn">Login as Bank Employee</a>
			<a href="/openLoginPageBankMgr" class="btn">Login as Bank Manager</a>
			<a href="/openLoginPageRegionalMgr" class="btn">Login as Regional
				Manager</a>
		</div>

		<p>
			New here? <a href="/userRegistration">Register Now</a>
		</p>
	</section>


	<%@include file="message.jsp"%>
	<footer>
		<p>&copy; 2024 XYZ Bank. All rights reserved.</p>
	</footer>
</body>
</html>

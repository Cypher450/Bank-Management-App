<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page import="bank.app.entities.Account"%>
<%@page import="bank.app.entities.User"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Account Details - XYZ Bank</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fullaccountdetails.css">
</head>
<body>

	<%
	String accName = null;
	User userDetails = (User) session.getAttribute("userDetails");
	System.out.print(userDetails);
	Account accountDetails = (Account) session.getAttribute("accountDetails");
	if(accountDetails.getAccountTypeId() == 1) 
		accName = "Savings";
	else 
		accName = "Current";
	%>

	<header>
		<h1>XYZ Bank - Account Details</h1>
	</header>

	<nav>
		<a href="/customer/dashboard">Dashboard</a> 
		<a href="/customer/account-details">Accounts</a> 
		<a href="/customer/view-profile/<%= userDetails.getUsername() %>>">Profile</a>
		<a href="/logout">Logout</a>
	</nav>

	<div class="account-details-container">
		<h2>Savings Account Details</h2>

		<div class="account-detail">
			<p>
				<strong>Account Holder Name:</strong> <%= userDetails.getFirstName()+ " " + userDetails.getLastName()%>
			</p>
		</div>
		<div class="account-detail">
			<p>
				<strong>Account Number:</strong> <%= accountDetails.getAccountNumber() %>
			</p>
		</div>
		<div class="account-detail">
			<p>
				<strong>Account Type:</strong> <%= accName %>
			</p>
		</div>
		<div class="account-detail">
			<p>
				<strong>IFSC Code:</strong> <%= accountDetails.getIfscCode() %>
			</p>
		</div>
		<div class="account-detail">
			<p>
				<strong>Available Balance:</strong> <%= accountDetails.getBalance() %>
			</p>
		</div>
		<div class="account-detail">
			<p>
				<strong>Account Opening Date:</strong> <%= accountDetails.getOpenedDate() %>
			</p>
		</div>
	</div>

	<footer>
		<p>&copy; 2024 XYZ Bank. All rights reserved.</p>
	</footer>

</body>
</html>
<%@page import="java.util.List"%>
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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Customer Dashboard - XYZ Bank</title>
<link rel="stylesheet" href="../css/customerdash.css">
</head>
<body>
	<%
	Account savingsAccount = null;
	User userDetails = (User) session.getAttribute("userDetails");
	savingsAccount = (Account) session.getAttribute("savingsAcc");
	
	String accName = null;
	if(savingsAccount.getAccountTypeId() == 1){
		accName = "Savings Account";
	} else{
		accName = "Current Account";
	}
	%>

	<header>
		<h1>Customer Dashboard - XYZ Bank</h1>
		<p>Welcome back! Manage your banking needs efficiently.</p>
	</header>

	<nav>
		<a href="/customer/dashboard">Dashboard</a> <a
			href="/customer/view-profile/<%=userDetails.getUsername()%>">Profile</a>
		<a href="/customer/openAccountPage">Open Account</a> <a href="/logout"
			class="btn">Logout</a>
	</nav>

	<div class="dashboard-container">
		<div class="dashboard-card">
			<h2>Account Overview</h2>
			<div class="account-balance"><%= savingsAccount.getBalance() %></div>
			<div class="account-details">
				<p>Account Number: <%= savingsAccount.getAccountNumber() %></p>
				<p>Account Type: <%= accName %></p>
			</div>
			<button class="btn" onclick="window.location.href = '/customer/select-account';">View
				Transactions</button>
			<button class="btn"
				onclick="window.location.href = '/customer/account-details';">Account
				Details</button>
		</div>

		<div class="dashboard-card">
			<h2>Profile Information</h2>
			<div class="profile-info">
				<p>
					Name:
					<%=userDetails.getFirstName() + " " + userDetails.getLastName()%></p>
				<p>
					Email:
					<%=userDetails.getEmail()%></p>
				<p>
					Phone:
					<%=userDetails.getPhone()%></p>
			</div>
			<button class="btn"
				onclick="window.location.href = '/customer/edit-profile';">Edit
				Profile</button>
		</div>

		<div class="dashboard-card">
			<h2>Services</h2>
			<button class="btn" onclick="window.location.href = '/deposit';">Deposit
				Money</button>
			<button class="btn" onclick="window.location.href = '/withdraw';">Withdraw
				Money</button>
			<button class="btn" onclick="window.location.href = '/coming-soon';">Account
				Statement</button>
		</div>
	</div>

	<footer>
		<p>&copy; 2024 XYZ Bank. All rights reserved.</p>
	</footer>

</body>
</html>
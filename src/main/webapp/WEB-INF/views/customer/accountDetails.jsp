<%@page import="java.util.List"%>
<%@page import="bank.app.entities.User"%>
<%@page import="bank.app.entities.Account"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Account Details - XYZ Bank</title>
<link rel="stylesheet" href="../css/accountdetails.css">
</head>
<body>
	<%
	Account accDetailsSavings = null;
	Account accDetailsCurrent = null;
	User userDetails = (User) session.getAttribute("userDetails");

	List<Account> accounts = (List<Account>) session.getAttribute("accountLists");

	if (accounts.size() == 2) {
		accDetailsSavings = (Account) session.getAttribute("savingsAcc");
		accDetailsCurrent = (Account) session.getAttribute("currentAcc");
	} else {
		if (accounts.get(0).getAccountTypeId() == 1) {
			accDetailsSavings = (Account) session.getAttribute("savingsAcc");
		} else {
			accDetailsCurrent = (Account) session.getAttribute("currentAcc");
		}
	}
	%>

	<header>
		<h1>XYZ Bank - Account Details</h1>
	</header>

	<nav>
		<a href="/customer/dashboard">Dashboard</a> <a
			href="/customer/view-profile/<%=userDetails.getUsername()%>">Profile</a>
		<a href="/logout">Logout</a>
	</nav>


	<%
	if (accounts.size() == 2) {
	%>

	<div class="accounts-container">
		<!-- Savings Account -->
		<div class="account-card">
			<h2>Savings Account</h2>
			<div class="account-details">
				<p>
					<strong>Amount:</strong>
					<%=accDetailsSavings.getBalance()%>
				</p>
				<p>
					<strong>IFSC Code:</strong>
					<%=accDetailsSavings.getIfscCode()%>
				</p>
				<p>
					<strong>Account Number:</strong>
					<%=accDetailsSavings.getAccountNumber()%>
				</p>
			</div>
			<button class="btn"
				onclick="window.location.href = '/customer/full-account-details/savings';">View
				Details</button>
		</div>

		<!-- Current Account -->
		<div class="account-card">
			<h2>Current Account</h2>
			<div class="account-details">
				<p>
					<strong>Amount:</strong>
					<%=accDetailsCurrent.getBalance()%>
				</p>
				<p>
					<strong>IFSC Code:</strong>
					<%=accDetailsCurrent.getIfscCode()%>
				</p>
				<p>
					<strong>Account Number:</strong>
					<%=accDetailsCurrent.getAccountNumber()%>
				</p>
			</div>
			<button class="btn"
				onclick="window.location.href = '/customer/full-account-details/current';">View
				Details</button>
		</div>
	</div>


	<%
	} else if (accounts.size() == 1) {
	if (accounts.get(0).getAccountTypeId() == 1) {
	%>

	<div class="accounts-container">
		<!-- Savings Account -->
		<div class="account-card">
			<h2>Savings Account</h2>
			<div class="account-details">
				<p>
					<strong>Amount:</strong>
					<%=accDetailsSavings.getBalance()%>
				</p>
				<p>
					<strong>IFSC Code:</strong>
					<%=accDetailsSavings.getIfscCode()%>
				</p>
				<p>
					<strong>Account Number:</strong>
					<%=accDetailsSavings.getAccountNumber()%>
				</p>
			</div>
			<button class="btn"
				onclick="window.location.href = '/customer/full-account-details/savings';">View
				Details</button>
		</div>

		<!-- Current Account -->
		<div class="account-card">
			<h2>Current Account</h2>
			<div class="account-details">
				<p>
					<strong>Amount:</strong> N/A
				</p>
				<p>
					<strong>IFSC Code:</strong> N/A
				</p>
				<p>
					<strong>Account Number:</strong> N/A
				</p>
			</div>

		</div>
	</div>
	<%
	} 
	}
	%>

	<%@include file="../message.jsp"%>
	<footer>
		<p>&copy; 2024 XYZ Bank. All rights reserved.</p>
	</footer>

</body>
</html>
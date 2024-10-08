<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bank.app.entities.User"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Profile</title>
<link rel="stylesheet" href="/css/editprofile.css">
<script src="../js/editProfilePageValidation.js"></script>
</head>
<body>

	<%
	User userDetails = (User) session.getAttribute("userDetails");
	%>

	<header>
		<h1>XYZ Bank</h1>
	</header>

	<!-- Navigation Bar -->
	<nav>
		<a href="/${role.getRoleName().toLowerCase()}/dashboard">Dashboard</a>
		<a
			href="/${role.getRoleName().toLowerCase()}/view-profile/<%= userDetails.getUsername() %>">Profile</a>
		<a href="/logout">Logout</a>
	</nav>

	<div class="login-container">
		<h2>Edit Profile</h2>
		<form action="/customer/updateDetails" method="POST"
			onsubmit="return validateForm()">
			<div class="input-group">
				<label for="firstName">First Name</label> <input type="text"
					id="firstName" name="firstName"
					value="<%=userDetails.getFirstName()%>"> <b><span
					id="firstName-error" style="color: red;"></span></b>
			</div>
			<div class="input-group">
				<label for="lastName">Last Name</label> <input type="text"
					id="lastName" name="lastName"
					value="<%=userDetails.getLastName()%>"> <b><span
					id="lastName-error" style="color: red;"></span></b>
			</div>
			<div class="input-group">
				<label for="email">Email</label> <input type="email" id="email"
					name="email" value="<%=userDetails.getEmail()%>"> <b><span
					id="email-error" style="color: red;"></span></b>
			</div>
			<div class="input-group">
				<label for="dateOfBirth">Date of Birth</label> <input type="date"
					id="dateOfBirth" name="dateOfBirth"
					value="<%=userDetails.getDateOfBirth()%>"> <b><span
					id="dob-error" style="color: red;"></span></b>
			</div>
			<div class="input-group">
				<label for="address">Address</label> <input type="text" id="address"
					name="address" value="<%=userDetails.getAddress()%>"> <b><span
					id="address-error" style="color: red;"></span></b>
			</div>
			<button type="submit" class="btn">Update</button>
		</form>
	</div>

	<%@include file="message.jsp"%>

	<footer>
		<p>© 2024 XYZ Bank. All rights reserved.</p>
	</footer>

</body>
</html>
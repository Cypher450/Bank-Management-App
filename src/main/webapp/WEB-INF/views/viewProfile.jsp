<%@page import="bank.app.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <title>Customer Profile</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/viewprofile.css">

	</head>
	<body>
		<%
					User userDetails = (User)request.getAttribute("userDetails");
				%>
	    <header>
	        <h1>Customer Profile</h1>
	    </header>
		
	    <nav>
	        <a href="/customerDashboard">Dashboard</a>
	        <a href="/coming-soon">Accounts</a>
	        <a href="/coming-soon">Transactions</a>
	        <a href="/logout" class="logout-btn">Logout</a>
	    </nav>

	    <div class="profile-container">
	        <div class="profile-header">
	            <h2>Your Profile Information</h2>
	        </div>

	        <form>
	            <div class="profile-info">
	                <label for="username">Username:</label>
	                <input type="text" id="username" name="username" value="<%=userDetails.getUsername()%>" readonly>
	            </div>

	            <div class="profile-info">
	                <label for="first_name">First Name:</label>
	                <input type="text" id="first_name" name="first_name" value="<%=userDetails.getFirstName()%>" readonly>
	            </div>

	            <div class="profile-info">
	                <label for="last_name">Last Name:</label>
	                <input type="text" id="last_name" name="last_name" value="<%=userDetails.getLastName()%>" readonly>
	            </div>

	            <div class="profile-info">
	                <label for="email">Email:</label>
	                <input type="email" id="email" name="email" value="<%=userDetails.getEmail()%>" readonly>
	            </div>

	            <div class="profile-info">
	                <label for="phone">Phone:</label>
	                <input type="tel" id="phone" name="phone" value="<%=userDetails.getPhone()%>" readonly>
	            </div>

	            <div class="profile-info">
	                <label for="address">Address:</label>
	                <textarea id="address" name="<%=userDetails.getAddress()%>" readonly><%=userDetails.getAddress()%></textarea>
	            </div>

	            <div class="btn-container">
	                <input type="button" class="btn"  onclick="window.location.href='/edit-profile'"  value="Edit Profile Details"/>
					
					<input type="button" class="btn"  onclick="window.location.href='/change-password'"  value="Change Password"/>
	            </div>
	        </form>
	    </div>
	</body>
	</html>
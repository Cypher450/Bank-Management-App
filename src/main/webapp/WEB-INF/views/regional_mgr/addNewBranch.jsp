<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New Branch</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/addNewBranch.css">
	<script src="${pageContext.request.contextPath}/js/addNewBranchValidation.js"></script>
</head>
<body>

<%

    int regionId = (int) request.getAttribute("regionId");


%>

	<!-- Navigation Bar -->
	<nav>
		<a href="/regional_mgr/dashboard">Dashboard</a> <a
			href="/regional_mgr/manageBranchManagers">Manage Branch Managers</a>
		<a href="/regional_mgr/managerApprovalList">Approval List</a> <a
			href="/regional_mgr/view-profile/${userDetails.getUsername()}">Profile</a>
		<a href="/logout">Logout</a>
	</nav>
	<div class="container">
		<h1>Add New Branch</h1>

		<form action="/regional_mgr/addNewBranch" method="POST">
			<label for="branchName">Branch Name:</label> 
			   <input type="text" id="branchName" name="branchName" required oninput="validateBranchName()"> 
			   <span id="branchError" style="color:red;"></span>
			   
			<label for="address">Address:</label> 
			   <input type="text" id="address" name="address" required oninput="validateAddress()"> 
			   <span id="addressError" style="color:red;"></span>
			   
			<label for="phone">Phone:</label> 
			   <input type="text" id="phone" name="phone" required oninput="validatePhone()"> 
			   <span id="phoneError" style="color:red;"></span>
			   
			<label for="pinCode">Pin Code:</label> 
			   <input type="text" id="pinCode" name="pinCode" required oninput="validatePinCode()"> 
			   <span id="pinError" style="color:red;"></span>
			   
			<label for="regionId">Region ID:</label> 
			   <input type="text" id="regionId" name="regionId" value="<%= regionId %>" required readonly> 
			   
			   <input type="submit" value="Add Branch">
		</form>
	</div>
	<%@include file="../message.jsp"%>
	<!-- Footer -->
	<footer>
		<p>© 2024 XYZ Bank. All rights reserved.</p>
	</footer>
</body>
</html>

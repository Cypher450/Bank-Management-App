<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Regional Manager Dashboard</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/regionalmanagerdash.css">

</head>
<body>

	<header>
		<h1>XYZ Bank - Regional Manager Dashboard</h1>
	</header>

	<!-- Navigation Bar -->
	<nav>
<<<<<<< HEAD
		<a href="/regional_mgr/dashboard">Dashboard</a>
		<a href="/regional_mgr/manageBranchManagers">Manage Bank Managers</a> 
		<a href="/regional_mgr/managerApprovalList">Approval List</a> 
		<a href="/regional_mgr/view-profile/${userDetails.getUsername()}">Profile</a>
=======
		<a href="/regional_mgr/dashboard">Dashboard</a> <a
			href="/regional_mgr/manageBranchManagers">Manage Bank Managers</a> <a
			href="#employees">Approval List</a> <a
			href="/regional_mgr/view-profile/${userDetails.getUsername()}">Profile</a>
>>>>>>> 2f46453daaa3dc0d983132527cafa0bbbaa1f3c1
		<a href="/logout">Logout</a>
	</nav>

	<div class="dashboard-container">
		<h2>Welcome, Regional Manager</h2>

		<!-- Overview Section -->
		<section class="overview">
			<h3>Overview</h3>
			<div class="stats">
				<div class="stat">
					<h4>Total Branches</h4>
					<p>${branches.size()}</p>
				</div>
				<div class="stat">
					<h4>Total Employees</h4>
					<p>${employees.size()}</p>
				</div>
				<div class="stat">
					<h4>Total Customers</h4>
					<p>${customers.size()}</p>
				</div>
			</div>
		</section>

		
		<!-- Quick Actions Section -->
		<section class="quick-actions">
			<h3>Quick Actions</h3>
			<div class="actions">

				<button onclick="window.location.href='/regional_mgr/addNewBranch';">Add
					New Branch</button>

				<button
					onclick="window.location.href = '/regional_mgr/manageBranchManagers';">Manage
					Bank Managers</button>
				<button  onclick="window.location.href = '/regional_mgr/managerApprovalList';">Approval List</button>
			</div>
		</section>
	</div>

	<%@include file="../message.jsp"%>
	<!-- Footer -->
	<footer>
		<p>© 2024 XYZ Bank. All rights reserved.</p>
	</footer>

</body>
</html>

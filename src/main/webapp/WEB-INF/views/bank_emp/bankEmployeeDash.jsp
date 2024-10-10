<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Branch Employee Dashboard</title>
<link rel="stylesheet" href="../css/bankemployeedash.css">

</head>
<body>

	<header>
		<h1>XYZ Bank - Branch Employee Dashboard</h1>
	</header>

	<!-- Navigation Bar -->
	<nav>
		<a href="#tasks">My Tasks</a> 
		<a href="/bank_emp/manageCustomers">Manage Customers</a> 
		<a href="#reports">Approve List</a> 
		<a href="/bank_emp/view-profile/${userDetails.getUsername()}">Profile</a>
		<a href="/logout">Logout</a>
	</nav>

	<div class="dashboard-container">
		<h2>Welcome, Employee</h2>

		<!-- Overview Section -->
		<section class="overview">
			<h3>Employee Overview</h3>
			<div class="stats">
				<div class="stat">
					<h4>Total Customers Served</h4>
					<p>150</p>
				</div>
				<div class="stat">
					<h4>Pending Tasks</h4>
					<p>5</p>
				</div>
			</div>
		</section>

		<!-- Quick Actions Section -->
		<section class="quick-actions">
			<h3>Quick Actions</h3>
			<div class="actions">
				<button>Add New Customer</button>
				<button>View Customer Details</button>
				<button>Approve List</button>
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

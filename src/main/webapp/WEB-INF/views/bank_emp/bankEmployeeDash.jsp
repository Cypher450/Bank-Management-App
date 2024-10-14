<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Branch Employee Dashboard</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bankemployeedash.css">

</head>
<body>

	<header>
		<h1>XYZ Bank - Bank Employee Dashboard</h1>
	</header>

	<!-- Navigation Bar -->
	<nav>
		<a href="/bank_emp/manageCustomers">Manage Customers</a> <a
			href="/bank_emp/approval-list">Approval List</a> <a
			href="/bank_emp/view-profile/${userDetails.getUsername()}">Profile</a>
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
					<p>${customers.size()}</p>
				</div>
			</div>
		</section>

		<!-- Quick Actions Section -->
		<section class="quick-actions">
			<h3>Quick Actions</h3>
			<div class="actions">

				<button class="btn"
					onclick="window.location.href='/bank_emp/open-acc-pending-list';">Open
					Account</button>
				<button class="btn"
					onclick="window.location.href='/bank_emp/manageCustomers';">Manage
					Customers</button>
				<button class="btn"
					onclick="window.location.href='/bank_emp/approval-list';">Approve
					List</button>
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

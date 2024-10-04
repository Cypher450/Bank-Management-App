<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Regional Manager Dashboard</title>
		<link rel="stylesheet" href="../css/regionalmanagerdash.css">

	</head>
	<body>

	<header>
	    <h1>XYZ Bank - Regional Manager Dashboard</h1>
	</header>

	<!-- Navigation Bar -->
	<nav>
	    <a href="/regional_mgr/dashboard">Dashboard</a>
	    <a href="#branches">Manage Branches</a>
	    <a href="#employees">Approval List</a>
	    <a href="/regional_mgr/view-profile/${userDetails.getUsername()}">Profile</a>
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
	                <p>45</p>
	            </div>
	            <div class="stat">
	                <h4>Total Employees</h4>
	                <p>120</p>
	            </div>
	            <div class="stat">
	                <h4>Total Customers</h4>
	                <p>90</p>
	            </div>
	        </div>
	    </section>

	  <!-- Chart Section -->
	    <section class="charts">
	        <h3>Branch Performance</h3>
	        <div class="chart-placeholder">
	            <p>Chart will go here</p>
	        </div>
	    </section>

	    <!-- Quick Actions Section -->
	    <section class="quick-actions">
	        <h3>Quick Actions</h3>
	        <div class="actions">
	            <button>Add New Branch</button>
	            <button>Manage Branches</button>
	            <button>Approval List</button>
	        </div>
	    </section>
	</div>

	<!-- Footer -->
	<footer>
	    <p>© 2024 XYZ Bank. All rights reserved.</p>
	</footer>

	</body>
	</html>

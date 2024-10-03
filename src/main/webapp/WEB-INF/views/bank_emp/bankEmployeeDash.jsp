<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Branch Employee Dashboard</title>
	    <style>
	        * {
	            margin: 0;
	            padding: 0;
	            box-sizing: border-box;
	        }

	        body {
	            font-family: Arial, sans-serif;
	            background-color: #f4f4f4;
	            display: flex;
	            flex-direction: column;
	            align-items: center;
	            justify-content: center;
	            min-height: 100vh;
	        }

	        header {
	            width: 100%;
	            background-color: #2c3e50; /* Dark blue */
	            color: white;
	            padding: 20px 0;
	            text-align: center;
	        }

	        h1 {
	            margin: 0;
	            font-size: 24px;
	        }

	        nav {
	            width: 100%;
	            background-color: #34495e; /* Darker blue */
	            padding: 15px 0;
	            display: flex;
	            justify-content: center;
	        }

	        nav a {
	            color: white;
	            padding: 15px 20px; /* Adjusted padding for better spacing */
	            text-decoration: none;
	            font-weight: bold;
	            text-transform: uppercase;
	            margin: 0 10px;
	            transition: background-color 0.3s;
	        }

	        nav a:hover {
	            background-color: #2980b9; /* Lighter blue */
	        }

	        .dashboard-container {
	            max-width: 800px;
	            margin: 20px;
	            padding: 20px;
	            background-color: white;
	            border-radius: 10px;
	            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	        }

	        h2 {
	            margin-bottom: 20px;
	            color: #333;
	        }

	        .overview {
	            margin-bottom: 20px;
	        }

	        .stats {
	            display: flex;
	            justify-content: space-between;
	        }

	        .stat {
	            background-color: #ecf0f1; /* Light gray */
	            padding: 15px;
	            border-radius: 5px;
	            text-align: center;
	            flex: 1;
	            margin: 0 10px;
	        }

	        .stat h4 {
	            margin-bottom: 10px;
	        }

	        .quick-actions {
	            margin-bottom: 20px;
	        }

	        .actions {
	            display: flex;
	            justify-content: space-between;
	        }

	        button {
	            padding: 10px;
	            background-color: #2980b9; /* Blue */
	            color: white;
	            border: none;
	            border-radius: 5px;
	            cursor: pointer;
	            flex: 1;
	            margin: 0 10px;
	        }

	        button:hover {
	            background-color: #1abc9c; /* Lighter green */
	        }

	        footer {
	            text-align: center;
	            margin-top: 20px;
	            padding: 20px;
	            background-color: #2c3e50; /* Dark blue */
	            color: white;
	            width: 100%;
	            position: relative;
	        }
	    </style>
	</head>
	<body>

	<header>
	    <h1>XYZ Bank - Branch Employee Dashboard</h1>
	</header>

	<!-- Navigation Bar -->
	<nav>
	    <a href="#tasks">My Tasks</a>
	    <a href="#customers">Manage Customers</a>
	    <a href="#reports">Approve List</a>
	    <a href="#reports">Profile</a>
	    <a href="#logout">Logout</a>
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

	<!-- Footer -->
	<footer>
	    <p>© 2024 XYZ Bank. All rights reserved.</p>
	</footer>

	</body>
	</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Register Page</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
	</head>
	<body>
	    <header>
	        <h1>XYZ Bank</h1>
	    </header>

	    <div class="register-container">
	        <h2>Register</h2>
	        <form action="/register" method="POST">
	            <div class="input-group">
	                <label for="firstName">FirstName</label>
	                <input type="text" id="firstName" name="firstName" required>
	            </div>
	            <div class="input-group">
	                <label for="lastName">LastName</label>
	                <input type="text" id="lastName" name="lastName" required>
	            </div>
	            <div class="input-group">
	                <label for="username">Username</label>
	                <input type="text" id="username" name="username" required>
	            </div>
				<div class="input-group">
	                <label for="password">Password</label>
	                <input type="password" id="password" name="password" required>
	            </div>
	            <div class="input-group">
	                <label for="email">Email</label>
	                <input type="email" id="email" name="email" required>
	            </div>
	            <div class="input-group">
	                <label for="dateOfBirth">DateOfBirth</label>
	                <input type="date" id="dateOfBirth" name="dateOfBirth" required>
	            </div>
	            <div class="input-group">
	                <label for="phone">Phone</label>
	                <input type="tel" id="phone" name="phone" required>
	            </div>
	            <div class="input-group">
	                <label for="branchId">Branch</label>
	                <select id="branchId" name="branchId" required>
						<option value="branch1">Select an Option</option>
	                    <option value="branch1">Branch 1</option>
	                    <option value="branch2">Branch 2</option>
	                    <option value="branch3">Branch 3</option>
	                    <option value="branch4">Branch 4</option>
	                </select>
	            </div>
	            <div class="input-group">
	                <label for="address">Address</label>
	                <input type="text" id="address" name="address" required>
	            </div>
	            <div class="input-group">
	                <label for="role">Role</label>
	                <select id="role" name="role" required>
						<option value="">Select an Option</option>
	                    <option value="customer">Customer</option>
	                    <option value="manager">Bank Employee</option>
						<option value="manager">Bank Manager</option>
						<option value="manager">Regional Manager</option>
	                </select>
	            </div>
	            <button type="submit">Register</button>
	        </form>
	        <p class="login-text">Already have an account? <a href="/comming-soon">Login here</a></p>
	    </div>

	    <footer class="footer">
	        <p>© 2024 XYZ Bank. All rights reserved.</p>
	    </footer>
	</body>
	</html>

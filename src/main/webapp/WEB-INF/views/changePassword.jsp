<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <title>Change Password</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/changepassword.css">

	</head>
	<body>
	    <header>
	        <h1>Change Password</h1>
	    </header>

	    <nav>
	        <a href="#">Dashboard</a>
	        <a href="#">Profile</a>
	        <a href="#">Accounts</a>
	        <a href="#">Logout</a>
	    </nav>

	    <div class="password-container">
	        <div class="password-header">
	            <h2>Update Your Password</h2>
	        </div>

	        <form>
	            <div class="password-info">
	                <label for="current_password">Current Password:</label>
	                <input type="password" id="current_password" name="current_password" required>
	            </div>

	            <div class="password-info">
	                <label for="new_password">New Password:</label>
	                <input type="password" id="new_password" name="new_password" required>
	            </div>

	            <div class="password-info">
	                <label for="confirm_password">Confirm New Password:</label>
	                <input type="password" id="confirm_password" name="confirm_password" required>
	            </div>

	            <div class="btn-container">
	                <button type="submit" class="btn">Change Password</button>
	            </div>
	        </form>
	    </div>
	</body>
	</html>
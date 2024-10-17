<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bank.app.entities.User"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Change Password</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/changepassword.css">
	<script src="${pageContext.request.contextPath}/js/changePasswordValidation.js"></script>
</head>
<body>
    <header>
        <h1>Change Password</h1>
    </header>

    <%
        User userDetails = (User) session.getAttribute("userDetails");
    %>

    <nav>
        <a href="/${role.getRoleName().toLowerCase()}/dashboard">Dashboard</a>
        <a href="/${role.getRoleName().toLowerCase()}/view-profile/<%= userDetails.getUsername() %>">Profile</a>
        <a href="/logout" class = "logout-btn">Logout</a>
    </nav>

    <div class="password-container">
        <div class="password-header">
            <h2>Update Your Password</h2>
        </div>

        <form action="/customer/change-password" method="POST" onsubmit="return newPasswordValidation()">
            <div class="password-info">
                <label for="current_password">Current Password:</label>
                <input type="password" id="currentPassword" name="currentPassword" required>
            </div>

			<div class="password-info">
			    <label for="new_password">New Password:</label>
			    <input type="password" id="newPassword" name="newPassword" required>
			</div>
			<b><span id="newPasswordError" style="color: red;"></span></b>

            <div class="password-info">
                <label for="confirm_password">Confirm New Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
			

            <div class="btn-container">
                <button type="submit" class="btn">Change Password</button>
            </div>
        </form>
        <%@include file="message.jsp" %>
    </div>
</body>
</html>

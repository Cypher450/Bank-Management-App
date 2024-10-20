<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<header>
		<h1>XYZ Bank</h1>
	</header>
	<div class="login-container">

		<h2>Login</h2>
		<form action="/login" method="POST">
			<input type="hidden" name="roleId" value="${roleId}" />
			<div class="input-group">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" required>
			</div>
			<div class="input-group">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" required>
			</div>
			<button type="submit">Login</button>
		</form>

		<button onclick="window.location.href='/auth/forgot-password'">Forgot Password</button>
		<p class="register-text">
			Not Registered? <a href="/userRegistration">Register Now</a>
		</p>
		<%@include file="message.jsp"%>
	</div>

	<footer class="footer">
		<p>&copy; 2024 XYZ Bank. All rights reserved.</p>
	</footer>
</body>
</html>

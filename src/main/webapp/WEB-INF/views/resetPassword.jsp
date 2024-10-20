<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Reset Password</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<h1>Reset Password</h1>
	<form action="/auth/reset-password" method="post">
		<input type="password" name="newPassword" placeholder="Enter new password" required />
		<button type="submit">Reset Password</button>
	</form>
	<%@include file="message.jsp"%>
</body>
</html>
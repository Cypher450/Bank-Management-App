<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Verify OTP</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<h1>Verify OTP</h1>
	<form action="/auth/verify-otp" method="post">
		<input type="number" name="otp" placeholder="Enter OTP" required />
		<button type="submit">Verify OTP</button>
	</form>
	<%@include file="message.jsp"%>
</body>
</html>
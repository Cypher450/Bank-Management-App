<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Transaction Successful</title>
</head>
<body>
	<%
	Double updatedBalance = (Double) session.getAttribute("updatedBalance");
	%>
	<h2>Transaction Successful</h2>
	<p>
		Your new balance is:<%=updatedBalance%>
	</p>
	<a href="/customer/dashboard">Go back to Dashboard</a>
</body>
</html>

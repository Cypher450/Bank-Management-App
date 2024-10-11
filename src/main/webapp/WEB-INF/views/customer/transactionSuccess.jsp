<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Transaction Successful</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/transactionSuccess.css">
</head>
<body>
	<div class="container">
		<%
		Double updatedBalance = (Double) session.getAttribute("updatedBalance");
		%>
		<h2>Transaction Successful</h2>
		<p>
			Your new balance is: ₹<%=updatedBalance%></p>
		<a href="/customer/dashboard">Go back to Dashboard</a>
	</div>
</body>
</html>

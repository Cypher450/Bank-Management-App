<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Bank Manager - XYZ Bank</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/editbankmanager.css">
</head>
<body>

	<header>
		<h1>XYZ Bank</h1>
	</header>

	<h2>Edit Bank Manager Information</h2>

	<form action="/regional_mgr/updateBankManager" method="post">
		<input type="hidden" name="userId" value="${bankManager.userId}">

		<label for="firstName">First Name:</label> <input type="text"
			id="firstName" name="firstName" value="${bankManager.firstName}"
			required> <label for="lastName">Last Name:</label> <input
			type="text" id="lastName" name="lastName"
			value="${bankManager.lastName}" required> <label for="email">Email:</label>
		<input type="email" id="email" name="email"
			value="${bankManager.email}" required> <label for="phone">Phone:
		</label> <input type="text" id="phone" name="phone"
			value="${bankManager.phone}" required> <label for="address">Address:</label>
		<input type="text" id="address" name="address"
			value="${bankManager.address}" required> <label
			for="dateOfBirth">Date of Birth:</label> <input type="date"
			id="dateOfBirth" name="dateOfBirth"
			value="${bankManager.dateOfBirth}" required>

		<button type="submit">Update</button>
	</form>
</body>
</html>
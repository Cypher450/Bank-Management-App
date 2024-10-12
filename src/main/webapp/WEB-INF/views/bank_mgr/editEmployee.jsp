<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Customer - XYZ Bank</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/editemployee.css">
</head>
<body>

	<header>
		<h1>XYZ Bank</h1>
	</header>

	<h2>Edit Employee Information</h2>

	<form action="/bank_mgr/updateEmployee" method="post">
		<input type="hidden" name="userId" value="${employee.userId}">

		<label for="firstName">First Name:</label> 
		<input type="text"
			id="firstName" name="firstName" value="${employee.firstName}"
			required> 
			
		<label for="lastName">Last Name:</label> 
		<input type="text" id="lastName" name="lastName"
			value="${employee.lastName}" required> 
			
		<label for="email">Email:</label>
		<input type="email" id="email" name="email" value="${employee.email}"
			required> 
		<label for="phone">Phone: </label> 
		<input type="text" id="phone" name="phone" value="${employee.phone}"
			required> 
			
		<label for="address">Address:</label> 
		<input type="text" id="address" name="address" value="${employee.address}"
			required> 
			
		<label for="dateOfBirth">Date of Birth:</label> 
		<input type="date" id="dateOfBirth" name="dateOfBirth"
			value="${employee.dateOfBirth}" required>

		<button type="submit">Update</button>
	</form>
    
</body>
</html>
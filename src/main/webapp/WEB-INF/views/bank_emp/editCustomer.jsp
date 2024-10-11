<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Customer - XYZ Bank</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/editcustomer.css">
</head>
<body>

	<header>
		<h1>XYZ Bank</h1>
	</header>

	<h2>Edit Customer Information</h2>

	<form action="/bank_emp/updateCustomer" method="post">
		<input type="hidden" name="userId" value="${customer.userId}">

		<label for="firstName">First Name:</label> 
		<input type="text"
			id="firstName" name="firstName" value="${customer.firstName}"
			required> 
			
		<label for="lastName">Last Name:</label> 
		<input type="text" id="lastName" name="lastName"
			value="${customer.lastName}" required> 
			
		<label for="email">Email:</label>
		<input type="email" id="email" name="email" value="${customer.email}"
			required> 
		<label for="phone">Phone: </label> 
		<input type="text" id="phone" name="phone" value="${customer.phone}"
			required> 
			
		<label for="address">Address:</label> 
		<input type="text" id="address" name="address" value="${customer.address}"
			required> 
			
		<label for="dateOfBirth">Date of Birth:</label> 
		<input type="date" id="dateOfBirth" name="dateOfBirth"
			value="${customer.dateOfBirth}" required>

		<button type="submit">Update</button>
	</form>
</body>
</html>
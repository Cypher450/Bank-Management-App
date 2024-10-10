<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Customer Info</title>
<link rel="stylesheet" href="/css/viewcustomerinfo.css">
</head>
<body>

	<h2>Customer Information</h2>

	<table>
		<tr>
			<th>Username</th>
			<td>${customer.username}</td>
		</tr>
		<tr>
			<th>First Name</th>
			<td>${customer.firstName}</td>
		</tr>
		<tr>
			<th>Last Name</th>
			<td>${customer.lastName}</td>
		</tr>
		<tr>
			<th>Email</th>
			<td>${customer.email}</td>
		</tr>
		<tr>
			<th>Phone</th>
			<td>${customer.phone}</td>
		</tr>
		<tr>
			<th>Address</th>
			<td>${customer.address}</td>
		</tr>
		<tr>
			<th>Date of Birth</th>
			<td>${customer.dateOfBirth}</td>
		</tr>
	</table>

	<a href="/bank_emp/manageCustomers" class="btn-back">Back to Manage
		Customers</a>

</body>
</html>
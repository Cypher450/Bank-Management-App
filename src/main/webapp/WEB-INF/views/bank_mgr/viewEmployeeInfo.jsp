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

	<h2>Employee Information</h2>

	<table>
		<tr>
			<th>Username</th>
			<td>${employee.username}</td>
		</tr>
		<tr>
			<th>First Name</th>
			<td>${employee.firstName}</td>
		</tr>
		<tr>
			<th>Last Name</th>
			<td>${employee.lastName}</td>
		</tr>
		<tr>
			<th>Email</th>
			<td>${employee.email}</td>
		</tr>
		<tr>
			<th>Phone</th>
			<td>${employee.phone}</td>
		</tr>
		<tr>
			<th>Address</th>
			<td>${employee.address}</td>
		</tr>
		<tr>
			<th>Date of Birth</th>
			<td>${employee.dateOfBirth}</td>
		</tr>
	</table>

	<a href="/bank_mgr/manageEmployees" class="btn-back">Back to Manage
		Employees</a>

</body>
</html>
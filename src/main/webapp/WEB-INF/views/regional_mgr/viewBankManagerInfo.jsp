<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Customer Info</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/viewbankmanagerinfo.css">
</head>
<body>

	<h2>Bank Manager Information</h2>

	<table>
		<tr>
			<th>Username</th>
			<td>${bankManager.username}</td>
		</tr>
		<tr>
			<th>First Name</th>
			<td>${bankManager.firstName}</td>
		</tr>
		<tr>
			<th>Last Name</th>
			<td>${bankManager.lastName}</td>
		</tr>
		<tr>
			<th>Email</th>
			<td>${bankManager.email}</td>
		</tr>
		<tr>
			<th>Phone</th>
			<td>${bankManager.phone}</td>
		</tr>
		<tr>
			<th>Address</th>
			<td>${bankManager.address}</td>
		</tr>
		<tr>
			<th>Date of Birth</th>
			<td>${bankManager.dateOfBirth}</td>
		</tr>
	</table>

	<a href="/regional_mgr/manageBranchManagers" class="btn-back">Back to Manage
		Bank Managers</a>

</body>
</html>
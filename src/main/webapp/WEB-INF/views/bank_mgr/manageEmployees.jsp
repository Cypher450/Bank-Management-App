<%@page import="bank.app.entities.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Customers</title>
<link rel="stylesheet" href="../css/managecustomers.css">
</head>
<body>

	<!-- Navbar -->
	<nav>
		<a href="/bank_mgr/dashboard">Dashboard</a> <a href="/logout">Logout</a>
	</nav>

	<h2>Manage Employees</h2>

	<%
	List<User> employees = (List<User>) session.getAttribute("employees");
	%>

	<table>
		<thead>
			<tr>
				<th>Username</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (employees.size() != 0) {
				for (User employee : employees) {
			%>
			<tr>
				<td><%=employee.getUsername()%></td>
				<td><%=employee.getFirstName()%></td>
				<td><%=employee.getLastName()%></td>
				<td><%=employee.getEmail()%></td>
				<td class="action-links"><a
					href="/bank_mgr/editEmployee/<%=employee.getUserId()%>">Edit</a>
					<button onclick="deleteEmployee(<%=employee.getUserId()%>)">Delete</button>
					<a href="/bank_mgr/viewEmployeeInfo/<%=employee.getUserId()%>">View
						Info</a></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="5" class="empty-message">No Employees available.</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<script>
        function deleteEmployee(userId) {
            if (confirm("Do you really want to delete this employee?")) {
                fetch('/bank_mgr/deleteEmployee', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: new URLSearchParams({ userId: userId })
                })
                .then(response => response.text())
                .then(result => {
                    if (result === 'success') {
                        alert("Employee deleted successfully!");
                        location.reload();
                    }
                });
            }
        }
    </script>
</body>
</html>
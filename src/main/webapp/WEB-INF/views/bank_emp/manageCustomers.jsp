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
		<a href="/bank_emp/dashboard">Dashboard</a> <a href="/logout">Logout</a>
	</nav>

	<h2>Manage Customers</h2>

	<%
	List<User> customers = (List<User>) session.getAttribute("customers");
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
			if (customers != null) {
				for (User customer : customers) {
			%>
			<tr>
				<td><%=customer.getUsername()%></td>
				<td><%=customer.getFirstName()%></td>
				<td><%=customer.getLastName()%></td>
				<td><%=customer.getEmail()%></td>
				<td class="action-links"><a
					href="/bank_emp/editCustomer/<%=customer.getUserId()%>">Edit</a>
					<button onclick="deleteCustomer(<%=customer.getUserId()%>)">Delete</button>
					<a href="/bank_emp/viewCustomerInfo/<%=customer.getUserId()%>">View
						Info</a></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="5" class="empty-message">No customers available.</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<script>
        function deleteCustomer(userId) {
            if (confirm("Do you really want to delete this customer?")) {
                fetch('/bank_emp/deleteCustomer', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: new URLSearchParams({ userId: userId })
                })
                .then(response => response.text())
                .then(result => {
                    if (result === 'success') {
                        alert("Customer deleted successfully!");
                        location.reload();
                    }
                });
            }
        }
    </script>

</body>
</html>
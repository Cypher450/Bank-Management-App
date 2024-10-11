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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/managebranchmanager.css">
</head>
<body>

	<!-- Navbar -->
	<nav>
		<a href="/regional_mgr/dashboard">Dashboard</a> <a href="/logout">Logout</a>
	</nav>

	<h2>Manage Bank Managers</h2>

	<%
	List<User> branchManagers = (List<User>) session.getAttribute("branchManagers");
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
			if (branchManagers.size() != 0) {
				for (User branchManager : branchManagers) {
			%>
			<tr>
				<td><%=branchManager.getUsername()%></td>
				<td><%=branchManager.getFirstName()%></td>
				<td><%=branchManager.getLastName()%></td>
				<td><%=branchManager.getEmail()%></td>
				<td class="action-links"><a
					href="/regional_mgr/editBankManager/<%=branchManager.getUserId()%>">Edit</a>
					<button onclick="deleteBankManager(<%=branchManager.getUserId()%>)">Delete</button>
					<a href="/regional_mgr/viewBankManagerInfo/<%=branchManager.getUserId()%>">View
						Info</a></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="5" class="empty-message">No Branch Manager Available.</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<script>
        function deleteBankManager(userId) {
            if (confirm("Do you really want to delete this branch manager?")) {
                fetch('/regional_mgr/deleteBankManager', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: new URLSearchParams({ userId: userId })
                })
                .then(response => response.text())
                .then(result => {
                    if (result === 'success') {
                        alert("Bank Manager deleted successfully!");
                        location.reload();
                    }
                });
            }
        }
    </script>
</body>
</html>
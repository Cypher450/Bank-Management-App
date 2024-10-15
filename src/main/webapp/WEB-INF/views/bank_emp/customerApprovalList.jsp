<%@ page import="bank.app.entities.User"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Approval List</title>
<link rel="stylesheet" href="../css/customerapprovallist.css">
<script>
	function handleApproval(button) {
		const actionCell = button.closest('td');
		button.disabled = true;
		return true; 
	}
</script>
</head>
<body>
	<header>
		<h1>Customers Needing Approval</h1>
	</header>

	<div class="approval-container">
		<h2>Approval List</h2>

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
				List<User> approvalList = (List<User>) session.getAttribute("customerApprovalList");

				if (approvalList != null && !approvalList.isEmpty()) {
					for (User customer : approvalList) {
				%>
				<tr>
					<td><%=customer.getUsername()%></td>
					<td><%=customer.getFirstName()%></td>
					<td><%=customer.getLastName()%></td>
					<td><%=customer.getEmail()%></td>
					<td>
						<form action="/bank_emp/customerApprove/<%=customer.getUserId()%>"
							method="post" style="display: inline-block;"
							onsubmit="return handleApproval(this.querySelector('button'));">
							<input type="hidden" name="userId"
								value="<%=customer.getUserId()%>">
							<button class="btn">Approve</button>
						</form>

						<form action="/bank_emp/customerReject/<%=customer.getUserId()%>" method="post"
							style="display: inline-block;">
							<input type="hidden" name="userId"
								value="<%=customer.getUserId()%>">
							<button class="btn">Reject</button>
						</form>
					</td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="5">No customers waiting for approval.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<div style="text-align: center; margin-top: 20px;">
		<button class="btn"
			onclick="window.location.href='/bank_emp/dashboard'">Go to
			Dashboard</button>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bank.app.entities.AccountType"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Open Your Bank Account</title>
<link rel="stylesheet" href="../css/openaccount.css">
</head>
<body>
	<header>
		<h1>Open Your Bank Account</h1>
	</header>

	<%
	List<AccountType> listOfAccountTypes = (List<AccountType>) session.getAttribute("listOfAccountTypes");
	%>

	<nav>
		<a href="/${role.getRoleName().toLowerCase()}/dashboard">Dashboard</a>
		<a href="/logout">Logout</a>
	</nav>

	<div class="account-container">
		<div class="account-header">
			<h2>Fill in Your Details</h2>
		</div>

		<form action="openAccountSuccess" Method="POST"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="balance">Initial Deposit (Minimum 1000):</label> <input
					type="number" id="balance" name="balance" min="1000" required>
			</div>

			<div class="form-group">
				<label for="openedDate">Date of Account Opening:</label> <input
					type="date" id="openedDate" name="openedDate" readonly>
			</div>

			<script>
				// Set today's date as the default
				const today = new Date().toISOString().substr(0, 10);
				document.getElementById("openedDate").value = today;
			</script>

			<div class="form-group">
				<label for="accountTypeId">Choose Account Type:</label> <select
					id="accountTypeId" name="accountTypeId" required>
					<option value="" disabled selected>Select Account Type</option>
					<%
					for (AccountType accountType : listOfAccountTypes) {
					%>
					<option value="<%=accountType.getAccountTypeId()%>">
						<%=accountType.getAccountType()%>
					</option>
					<%
					}
					%>
				</select>
			</div>

			<div class="form-group">
				<label for="idProof">ID Proof:</label> <input type="file"
					id="idProof" name="idProof" required>
			</div>

			<div class="form-group">
				<label for="password">Enter Password:</label> <input type="password"
					id="password" name="password" required>
			</div>

			<div class="btn-container">
				<button type="submit" class="btn">Submit Application</button>
			</div>
			<%@include file="../message.jsp"%>
		</form>
	</div>



</body>
</html>
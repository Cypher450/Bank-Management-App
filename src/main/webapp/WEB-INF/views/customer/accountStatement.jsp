
<%@page import="java.util.List"%>
<%@page import="bank.app.entities.Transaction"%>
<%@page import="bank.app.entities.Account"%>
<%@page import="bank.app.entities.Roles"%>
<%@page import="bank.app.entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Account Statement - XYZ Bank</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/accountstatement.css">
</head>
<body>
	<%
	User userDetails = (User) session.getAttribute("userDetails");
	List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
	Account accountDetails = (Account) request.getAttribute("accountDetails");
	Double totalDebits = (Double) request.getAttribute("totalDebits");
	Double totalCredits = (Double) request.getAttribute("totalCredits");
	%>

	<header>
		<h1>Account Statement - XYZ Bank</h1>
	</header>

	<div class="account-details">
		<h2>Account Information</h2>
		<div class="account-info">
			<p>
				<strong>Account Holder:</strong>
				<%=userDetails.getFirstName() + " " + userDetails.getLastName()%></p>
			<p>
				<strong>Account Number:</strong>
				<%=accountDetails.getAccountNumber()%></p>
			<p>
				<strong>Account Type:</strong>
				<%=(accountDetails.getAccountTypeId() == 1) ? "Savings" : "Current"%></p>
			<p>
				<strong>IFSC Code:</strong>
				<%=accountDetails.getIfscCode()%></p>
		</div>

		<h2>Account Summary</h2>
		<div class="summary">
			<p>
				<strong>Total Credits:</strong> ₹<%= totalCredits %>
			</p>
			<p>
				<strong>Total Debits:</strong> ₹<%= totalDebits %>
			</p>
			<p>
				<strong>Closing Balance:</strong> ₹<%= accountDetails.getBalance() %>
			</p>
		</div>
	</div>

	<div class="transaction-details">
		<h2>Transaction History</h2>

		<!-- Transaction Table -->
		<table>
			<tr>
				<th>Account Number</th>
				<th>Transaction Type</th>
				<th>Amount (₹)</th>
				<th>Transaction Time</th>
			</tr>

			<%
			for (Transaction transaction : transactions) {
			%>
			<tr class="transaction-row">

				<td><%=transaction.getAccountNo()%></td>
				<td
					style="color: <%=(transaction.getTransactionType() == 1) ? "green" : "red"%>;">
					<strong><%=(transaction.getTransactionType() == 1) ? "Deposit" : "Withdraw"%></strong>
				</td>
				<td><%=transaction.getAmount()%></td>
				<td><%=transaction.getTransactionTime()%></td>

			</tr>
			<%
			}
			%>

		</table>

		<!-- No transactions message (if applicable) -->
		<div class="no-transactions">No transactions available for this
			period.</div>
	</div>

	<!-- Print Button -->
	<div class="print-btn">
		<button onclick="window.print()">Print Statement</button>
		<button onclick="window.location.href='/customer/dashboard'">Go
			to Dashboard</button>

	</div>

	<%@include file="../message.jsp"%>
	<!-- Footer -->
	<div class="footer">
		<p>&copy; 2024 XYZ Bank. All rights reserved.</p>
	</div>

</body>
</html>
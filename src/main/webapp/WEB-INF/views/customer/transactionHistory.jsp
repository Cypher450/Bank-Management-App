<%@page import="bank.app.entities.Transaction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bank.app.entities.AccountType"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Transactions</title>
<link rel="stylesheet" href="../css/transactionhistory.css">
</head>
<body>

	<%
	List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
	%>

	<div class="container">
		<h1>Transaction History (Last 10 Transactions)</h1>
		<table>
			<thead>
				<tr>
					<th>Account Number</th>
					<th>Transaction Type</th>
					<th>Amount (₹)</th>
					<th>Transaction Time</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Transaction transaction : transactions) {
				%>
				<tr class="transaction-row">

					<td><%=transaction.getAccountNo()%></td>
					<td style="color: <%= (transaction.getTransactionType() == 1) ? "green" : "red" %>;">
                        <strong><%= (transaction.getTransactionType() == 1) ? "Deposit" : "Withdraw" %></strong>
                    </td>
					<td><%=transaction.getAmount()%></td>
					<td><%=transaction.getTransactionTime()%></td>

				</tr>
				<%
				}
				%>

			</tbody>
		</table>

		<a class="back-link" href="/customer/dashboard">Back to Dashboard</a>
	</div>

</body>
</html>
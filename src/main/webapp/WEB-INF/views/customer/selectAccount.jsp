<!DOCTYPE html>
<%@page import="bank.app.entities.Account"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Select Account for Transactions</title>
<link rel="stylesheet" href="../css/selectaccount.css">
</head>
<body>

	<%
	Account accDetailsSavings = (Account) session.getAttribute("savingsAcc");
	Account accDetailsCurrent = (Account) session.getAttribute("currentAcc");
	%>

	<div class="container">
		<h1>Select Account</h1>

		<form id="accountForm" action="/transaction-history" method="GET">
			<label for="accountSelect">Choose Account:</label> <select
				id="accountSelect" name="accountNo" required>
				<option value="">-- Select an Account --</option>
				<% if(accDetailsCurrent != null && accDetailsSavings != null ){ %>
				
				     <option value="<%= accDetailsSavings.getAccountNumber()%>">Savings
					Account -
					<%=accDetailsSavings.getAccountNumber()%></option>
				<option value="<%= accDetailsCurrent.getAccountNumber()%>">Current
					Account -
					<%=accDetailsCurrent.getAccountNumber()%></option>
				
				<% } else if (accDetailsCurrent == null && accDetailsSavings != null) {%>
				
				     <option value="<%= accDetailsSavings.getAccountNumber()%>">Savings
					Account -
					<%=accDetailsSavings.getAccountNumber()%></option>
				      
				<%} else { %>
				
				<option value="<%= accDetailsCurrent.getAccountNumber()%>">Current
					Account -
					<%=accDetailsCurrent.getAccountNumber()%></option>
				    
				<%} %>
			</select>
			<button type="submit" class="btn">View Transactions</button>
		</form>
	</div>
	
	<%@include file="../message.jsp" %>

</body>
</html>
<%@page import="bank.app.entities.Account"%>


<%
Account accDetailsSavings = (Account) session.getAttribute("savingsAcc");
Account accDetailsCurrent = (Account) session.getAttribute("currentAcc");
%>


<label for="accountSelect">Choose Account:</label>
<select id="accountSelect" name="accountNo" required>
	<option value="">-- Select an Account --</option>
	<%
	if (accDetailsCurrent != null && accDetailsSavings != null) {
	%>

	<option value="<%=accDetailsSavings.getAccountNumber()%>">Savings
		Account -
		<%=accDetailsSavings.getAccountNumber()%></option>
	<option value="<%=accDetailsCurrent.getAccountNumber()%>">Current
		Account -
		<%=accDetailsCurrent.getAccountNumber()%></option>

	<%
	} else if (accDetailsCurrent == null && accDetailsSavings != null) {
	%>

	<option value="<%=accDetailsSavings.getAccountNumber()%>">Savings
		Account -
		<%=accDetailsSavings.getAccountNumber()%></option>

	<%
	} else {
	%>

	<option value="<%=accDetailsCurrent.getAccountNumber()%>">Current
		Account -
		<%=accDetailsCurrent.getAccountNumber()%></option>

	<%
	}
	%>
</select>
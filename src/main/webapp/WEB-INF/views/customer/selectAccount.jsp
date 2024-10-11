
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Select Account for Transactions</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/selectaccount.css">
</head>
<body>

	<div class="container">
		<h1>Select Account</h1>

		<form id="accountForm" action="/transaction-history" method="GET">
			<%@include file = "selectAccContent.jsp"%>
			<button type="submit" class="btn">View Transactions</button>
		</form>
	</div>
	
	<%@include file="../message.jsp" %>

</body>
</html>
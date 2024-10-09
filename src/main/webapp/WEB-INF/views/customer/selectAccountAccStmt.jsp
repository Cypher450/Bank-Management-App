
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Select Account for Transactions</title>
<link rel="stylesheet" href="../css/selectaccount.css">
</head>
<body>

	<div class="container">
		<h1>Select Account</h1>

		<form id="accountForm" action="/customer/account-Statement" method="GET">
			<%@include file = "selectAccContent.jsp"%>
			<button type="submit" class="btn">View Account Statement</button>
		</form>
	</div>
	
	<%@include file="../message.jsp" %>

</body>
</html>
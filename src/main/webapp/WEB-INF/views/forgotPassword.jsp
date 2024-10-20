<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Forgot Password</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<h1>Forgot Password</h1>
	<form id="forgotPasswordForm" action="/auth/send-email" method="GET" onsubmit="return validateEmail()">
		<input type="hidden" name="roleId" value="${roleId}" id="roleIdInput" />
		<input type="email" id="emailInput" name="to" placeholder="Enter your email" required />
		<div id="emailError" style="color: red; display: none;"></div>
		<button type="submit">Send OTP</button>
	</form>

	<script>
	function validateEmail() {
	    const emailInput = document.getElementById("emailInput").value;
	    const errorDiv = document.getElementById("emailError");
	    const roleIdInput = document.getElementById("roleIdInput").value;

	    // Clear previous error message
	    errorDiv.textContent = "";
	    errorDiv.style.display = "none";

	    // Use fetch to send a GET request
	    fetch("/auth/checkEmail?email=" + emailInput+"&roleId="+roleIdInput)
	        .then(response => response.text())
	        .then(data => {
	            if (data === "exists") {
	                document.getElementById("forgotPasswordForm").submit();
	            } else {
	                errorDiv.textContent = "*Email does not exist.";
	                errorDiv.style.display = "block";
	            }
	        })
	        .catch(error => {
	            console.error('Error:', error);
	            errorDiv.textContent = "Network error. Please try again.";
	            errorDiv.style.display = "block";
	        });

	    // Prevent form submission until the fetch call completes
	    return false;
	}
	</script>
</body>
</html>

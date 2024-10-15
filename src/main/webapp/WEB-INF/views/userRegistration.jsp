<%@page import="bank.app.entities.Roles"%>
<%@page import="bank.app.entities.Branch"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/registration.css">
<script
	src="${pageContext.request.contextPath}/js/registerPageValidation.js"></script>
</head>
<body>
	<header>
		<h1>XYZ Bank</h1>
	</header>


	<%!String getUserFriendlyRoles(String technicalName) {
		if (technicalName.equals("Bank_MGR"))
			return "Bank Manager";
		else if (technicalName.equals("Bank_EMP"))
			return "Bank Employee";
		else if (technicalName.equals("Regional_MGR"))
			return "Regional Manager";
		else
			return "Customer";
	}%>


	<%
	List<Roles> rolesList = (List<Roles>) request.getAttribute("listOfRoles");

	List<Branch> branchList = (List<Branch>) request.getAttribute("listOfBranch");
	%>

	<div class="register-container">
		<h2>Register</h2>

		<form action="/register" method="POST"
			onsubmit="return validateForm()">

			<div class="input-group">
				<label for="firstName">FirstName</label> 
				<input type="text" id="firstName" name="firstName"> 
				<span id="firstName-error" style="color: red;"></span>
			</div>
			<div class="input-group">
				<label for="lastName">LastName</label> 
				<input type="text" id="lastName" name="lastName"> 
				<span id="lastName-error" style="color: red;"></span>
			</div>
			<div class="input-group">
				<label for="username">Username</label> 
				<input type="text" id="username" name="username" onkeyup="checkUsername()" required>
				<span id="username-error" style="color: red;"></span>
				<span id="usernameMessage"></span>
			</div>
			<div class="input-group">
				<label for="password">Password</label> 
				<input type="password" id="password" name="password"> 
				<span id="password-error" style="color: red;"></span>
			</div>
			<div class="input-group">
				<label for="email">Email</label> 
				<input type="email" id="email" name="email"> 
				<span id="email-error" style="color: red;"></span>
			</div>
			<div class="input-group">
				<label for="dateOfBirth">Date of Birth</label> 
				<input type="date" id="dateOfBirth" name="dateOfBirth"> 
				<span id="dob-error" style="color: red;"></span>
			</div>
			<div class="input-group">
				<label for="phone">Phone</label> 
				<input type="tel" id="phone" name="phone"> 
				<span id="phone-error" style="color: red;"></span>
			</div>
			<div class="input-group">
				<label for="branchId">Branch</label> 
				<select id="branchId" name="branchId">
					<option>Select an Option</option>
					<%
					for (Branch branch : branchList) {
					%>
					<option value="<%=branch.getBranchId()%>">
					  <p>Branch Id: <%=branch.getBranchId()%> & Branch Name: <%=branch.getBranchName()%></p>
					</option>
					<%
					}
					%>

				</select>
			</div>
			<div class="input-group">
				<label for="address">Address</label> 
				<input type="text" id="address" name="address"> 
				<span id="address-error" style="color: red;"></span>
			</div>
			<div class="input-group">
				<label for="role">Role</label> 
				<select id="role" name="roleId" required>
					<option>Select an Option</option>

					<%
					for (Roles role : rolesList) {
					%>
					<option value="<%=role.getRoleId()%>">
						<%=getUserFriendlyRoles(role.getRoleName())%>
					</option>
					<%
					}
					%>

				</select>
				 <span id="branchMessage"></span>
			</div>
			<button type="submit">Register</button>
		</form>
		<p class="login-text">
			Already have an account? <a href="/">Login here</a>
		</p>
	</div>

	<script type="text/javascript">
    function checkUsername() {
        var username = document.getElementById("username").value;

        // Use fetch to send a GET request
        fetch("/checkUsername?username=" + username)
            .then(response => response.text())
            .then(data => {
                var message = document.getElementById("usernameMessage");
                if (data === "exists") {
                    message.textContent = "*Username is already taken.";
                    message.style.color = "red";
                } else {
                    message.textContent = "*Username is available.";
                    message.style.color = "green";
                }
            })
            .catch(error => console.error('Error:', error));
    }
    
    document.getElementById("branchId").addEventListener("change", function() {
        var branchId = this.value;
        checkBankManager(branchId);
    });
    
    function checkBankManager(branchId) {
        fetch("/checkBankManager?branchId=" + branchId)
            .then(response => response.text())
            .then(data => {
                var roleDropdown = document.getElementById("role");
                var message = document.getElementById("branchMessage");

                if (data === "exists") {
                    for (let i = 0; i < roleDropdown.options.length; i++) {
                        if (roleDropdown.options[i].text.includes("Bank Manager")) {
                            roleDropdown.options[i].style.display = "none";  
                        }
                    }
                    message.textContent = "*Bank manager is already present for this branch.";
                    message.style.color = "red";
                } else {
                    for (let i = 0; i < roleDropdown.options.length; i++) {
                        if (roleDropdown.options[i].text.includes("Bank Manager")) {
                            roleDropdown.options[i].style.display = "block"; 
                        }
                    }
                    message.textContent = "";  
                }
            })
            .catch(error => console.error('Error:', error));
    }
   </script>

	<%@include file="message.jsp"%>

	<footer class="footer">
		<p>© 2024 XYZ Bank. All rights reserved.</p>
	</footer>
</body>
</html>

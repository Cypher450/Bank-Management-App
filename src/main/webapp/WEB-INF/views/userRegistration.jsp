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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
	</head>
	<body>
	    <header>
	        <h1>XYZ Bank</h1>
	    </header>
		
		
		<%!
				String getUserFriendlyRoles(String technicalName){
					if(technicalName.equals("Bank_MGR")) return "Bank Manager";
					else if (technicalName.equals("Bank_EMP")) return "Bank Employee";
					else if (technicalName.equals("Regional_MGR")) return "Regional Manager";
					else return "Customer";
				}
			%>
		

		<%
		    List<Roles> rolesList = (List<Roles>)request.getAttribute("listOfRoles");
			
			List<Branch> branchList = (List<Branch>)request.getAttribute("listOfBranch");
		
		%>

	    <div class="register-container">
	        <h2>Register</h2>
	        <form action="/register" method="POST">
	            <div class="input-group">
	                <label for="firstName">FirstName</label>
	                <input type="text" id="firstName" name="firstName" required>
	            </div>
	            <div class="input-group">
	                <label for="lastName">LastName</label>
	                <input type="text" id="lastName" name="lastName" required>
	            </div>
	            <div class="input-group">
	                <label for="username">Username</label>
	                <input type="text" id="username" name="username" required>
	            </div>
				<div class="input-group">
	                <label for="password">Password</label>
	                <input type="password" id="password" name="password" required>
	            </div>
	            <div class="input-group">
	                <label for="email">Email</label>
	                <input type="email" id="email" name="email" required>
	            </div>
	            <div class="input-group">
	                <label for="dateOfBirth">DateOfBirth</label>
	                <input type="date" id="dateOfBirth" name="dateOfBirth" required>
	            </div>
	            <div class="input-group">
	                <label for="phone">Phone</label>
	                <input type="tel" id="phone" name="phone" required>
	            </div>
	            <div class="input-group">
	                <label for="branchId">Branch</label>
	                <select id="branchId" name="branchId" required>
						<option>Select an Option</option>
						<%
				             for (Branch branch : branchList) {
						%>
								<option value="<%=branch.getBranchId()%>">
									     <%= branch.getBranchName()+" : "+branch.getBranchId() %> 
					            </option>
						<%
							}
						%>

	                </select>
	            </div>
	            <div class="input-group">
	                <label for="address">Address</label>
	                <input type="text" id="address" name="address" required>
	            </div>
	            <div class="input-group">
	                <label for="role">Role</label>
	                <select id="role" name="roleId" required>
						<option>Select an Option</option>
	         
						<%
						        for (Roles role : rolesList) {
						%>
						            <option  value="<%=role.getRoleId()%>">
						                <%=getUserFriendlyRoles(role.getRoleName()) %> 
						            </option>
						<%
						        }
						%>

						
						
						
	                </select>
	            </div>
	            <button type="submit">Register</button>
	        </form>
	        <p class="login-text">Already have an account? <a href="/">Login here</a></p>
	    </div>

	    <footer class="footer">
	        <p>© 2024 XYZ Bank. All rights reserved.</p>
	    </footer>
	</body>
	</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/editprofile.css">
   
</head>
<body>
<header>
        <h1>XYZ Bank</h1>
    </header>
    <div class="login-container">
	
        <h2>Edit profile</h2>
		<form action="/updateDetails" method="POST" modelAttribute="user">
			            <div class="input-group">
			                <label for="firstName">FirstName</label>
			                <input type="text" id="firstName" name="firstName">
							<b><span id="firstName-error" style="color:red;"></span></b>	
			            </div>
			            <div class="input-group">
			                <label for="lastName">LastName</label>
			                <input type="text" id="lastName" name="lastName" >
							<b><span id="lastName-error" style="color:red;"></span></b>
			            </div>
						
			            <div class="input-group">
			                <label for="email">Email</label>
			                <input type="email" id="email" name="email" >
							<b><span id="email-error" style="color:red;"></span></b>
			            </div>
			            <div class="input-group">
			                <label for="dateOfBirth">DateOfBirth</label>
			                <input type="date" id="dateOfBirth" name="dateOfBirth">
							<b><span id="dob-error" style="color:red;"></span></b>
			            </div>
			          
			      
			            <div class="input-group">
			                <label for="address">Address</label>
			                <input type="text" id="address" name="address">
							<b><span id="address-error" style="color:red;"></span></b>
			            </div>
			            
			            <button type="submit" class="btn">Update</button>
			        </form>

    </div>
	<footer class="footer">
        <p>© 2024 XYZ Bank. All rights reserved.</p>
    </footer>
	
	<script>
    document.getElementById('editProfileForm').addEventListener('submit', function(event) {
        // Prevent form submission
        event.preventDefault();
 
        // Clear previous error messages
        let errorMessages = document.querySelectorAll('.error');
        errorMessages.forEach(function(msg) {
            msg.textContent = '';
        });
 
        // Validate fields
        let isValid = true;
 
        // Username validation
        const username = document.getElementById('username').value;
        if (username === '') {
            document.getElementById('usernameError').textContent = 'Username is required.';
            isValid = false;
        }
 
        // First name validation
        const firstName = document.getElementById('firstName').value;
        if (firstName === '') {
            document.getElementById('firstNameError').textContent = 'First name is required.';
            isValid = false;
        }
 
        // Last name validation
        const lastName = document.getElementById('lastName').value;
        if (lastName === '') {
            document.getElementById('lastNameError').textContent = 'Last name is required.';
            isValid = false;
        }
 
        // Date of birth validation
        const dob = document.getElementById('dob').value;
        if (dob === '') {
            document.getElementById('dobError').textContent = 'Date of birth is required.';
            isValid = false;
        }
 
        // Email validation
        const email = document.getElementById('email').value;
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (email === '') {
            document.getElementById('emailError').textContent = 'Email is required.';
            isValid = false;
        } else if (!emailPattern.test(email)) {
            document.getElementById('emailError').textContent = 'Invalid email format.';
            isValid = false;
        }
 
        // Address validation
        const address = document.getElementById('address').value;
        if (address === '') {
            document.getElementById('addressError').textContent = 'Address is required.';
            isValid = false;
        }
 
        // If valid, submit the form (you can also add your backend submission logic here)
        if (isValid) {
            alert('Profile updated successfully!');
            // Uncomment the line below to submit the form
            // document.getElementById('editProfileForm').submit();
        }
    });
</script>
</body>
</html>

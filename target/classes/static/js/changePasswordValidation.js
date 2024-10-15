function newPasswordValidation() {
	const newPassword = document.getElementById("newPassword").value;
	const confirmPassword = document.getElementById("confirmPassword").value;
	const passwordError = document.getElementById("newPasswordError");

	let passwordErrors = [];
	const capitalLetterRegex = /[A-Z]/;
	const lowercaseLetterRegex = /[a-z]/;
	const specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/;
	const minLength = 8;

	passwordError.textContent = "";
	let isValid = true;  

	// Password validation
	if (newPassword.length < minLength) {
		passwordErrors.push("*Password should be more than 8 characters.");
		isValid = false;
	}

	if (!capitalLetterRegex.test(newPassword)) {
		passwordErrors.push("*Password should contain at least one capital letter.");
		isValid = false;
	}

	if (!lowercaseLetterRegex.test(newPassword)) {
		passwordErrors.push("*Password should contain at least one lowercase letter.");
		isValid = false;
	}

	if (!specialCharRegex.test(newPassword)) {
		passwordErrors.push("*Password should contain at least one special character.");
		isValid = false;
	}

	if (passwordErrors.length > 0) {
		passwordError.textContent = passwordErrors.join(" ");
		return false;  // Prevent form submission
	}
	
	// Check if newPassword matches confirmPassword
	if (newPassword !== confirmPassword) {
		alert("New Password and Confirm Password do not match.");
		return false;
	}

	return isValid;
}

function validateForm() {

	const firstName = document.getElementById("firstName").value;
	const lastName = document.getElementById("lastName").value;
	const email = document.getElementById("email").value;
	const dateOfBirth = document.getElementById("dateOfBirth").value;
	const address = document.getElementById("address").value;


	const firstNameError = document.getElementById("firstName-error");
	const lastNameError = document.getElementById("lastName-error");
	const emailError = document.getElementById("email-error");
	const dobError = document.getElementById("dob-error");
	const addressError = document.getElementById("address-error");


	firstNameError.textContent = "";
	lastNameError.textContent = "";
	usernameError.textContent = "";
	passwordError.textContent = "";
	emailError.textContent = "";
	dobError.textContent = "";
	phoneError.textContent = "";
	addressError.textContent = "";


	let isValid = true;


	const nameRegex = /^[A-Za-z\s]+$/;

	// First name validation

	let firstNameErrors = [];
	if (firstName.trim() === "") {
		firstNameErrors.push("*First name cann't be empty.");
		isValid = false;
	}

	if (firstName.length > 35) {
		firstNameErrors.push("*First name cann't be more than 35 characters.");
		isValid = false;
	}

	if (!nameRegex.test(firstName)) {
		firstNameErrors.push("*First name should contain only letters Not special character and number.");
		isValid = false;
	}

	if (firstNameErrors.length > 0) {
		alert(firstNameErrors.join("\n"));
		firstNameError.textContent = firstNameErrors.join(" ");
	}

	// Last name validation
	let lastNameErrors = [];

	if (lastName.length > 35) {
		lastNameErrors.push("*Last name cannot be more than 35 characters.");
		isValid = false;
	}

	if (lastName.trim() !== "" && !nameRegex.test(lastName)) {
		lastNameErrors.push("*Last name should contain only letters Not special characters  & number.");
		isValid = false;
	}

	if (lastNameErrors.length > 0) {
		alert(lastNameErrors.join("\n"));
		lastNameError.textContent = lastNameErrors.join(" ");
	}

	
	// Email validation (same as before)
	let emailErrors = [];
	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Simple email regex
	if (email.trim() === "") {
		emailErrors.push("*Email cannot be empty.");
		isValid = false;
	}
	if (!emailRegex.test(email)) {
		emailErrors.push("*Please enter a valid email format (e.g., aman@gmail.com).");
		isValid = false;
	}

	if (email.length > 64) {
		emailErrors.push("*Email cannot be more than 64 characters.");
		isValid = false;
	}



	if (emailErrors.length > 0) {
		alert(emailErrors.join("\n"));
		emailError.textContent = emailErrors.join(" ");
	}

	// Date of Birth validation for age >= 18
	if (dateOfBirth) {
		const today = new Date();
		const dob = new Date(dateOfBirth);
		const age = today.getFullYear() - dob.getFullYear();
		const monthDiff = today.getMonth() - dob.getMonth();
		const dayDiff = today.getDate() - dob.getDate();

		if (age < 18 || (age === 18 && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)))) {
			dobError.textContent = "You must be at least 18 years old.";
			alert("*You must be at least 18 years old.");
			isValid = false;
		}
	}
	
	// Address validation
		if (address.length > 255) {
			addressError.textContent = "Address cannot be more than 255 characters.";
			alert("Address cannot be more than 255 characters.");
			isValid = false;
		}

	return isValid;

}
function validateBranchName() {
	const input = document.getElementById('branchName');
	const errorSpan = document.getElementById('branchError');

	if (input.validity.valueMissing) {
		errorSpan.textContent = 'Branch name cannot be empty.';
	} else if (input.value.length < 6 || input.value.length > 34) {
		errorSpan.textContent = 'Branch name must be between 6 and 34 characters.';
	} else if (!/^[A-Za-z\s]+$/.test(input.value)) {
		errorSpan.textContent = 'Branch name can only contain letters.';
	} else {
		errorSpan.textContent = ''; // Clear error if valid
	}
}

function validateAddress() {
	const input = document.getElementById('address');
	const errorSpan = document.getElementById('addressError');

	if (input.value.length >= 255) {
		errorSpan.textContent = 'Address must be less than 255 characters.';
	} else {
		errorSpan.textContent = ''; // Clear error if valid
	}
}

function validatePhone() {
	const input = document.getElementById('phone');
	const errorSpan = document.getElementById('phoneError');

	if (!/^[6789]\d{9}$/.test(input.value)) {
		errorSpan.textContent = 'Phone number must be 10 digits and start with 6, 7, 8, or 9.';
	} else {
		errorSpan.textContent = ''; // Clear error if valid
	}
}

function validatePinCode() {
	const input = document.getElementById('pinCode');
	const errorSpan = document.getElementById('pinError');

	if (!/^\d{6}$/.test(input.value)) {
		errorSpan.textContent = 'Pin code must be exactly 6 digits.';
	} else {
		errorSpan.textContent = ''; // Clear error if valid
	}
}


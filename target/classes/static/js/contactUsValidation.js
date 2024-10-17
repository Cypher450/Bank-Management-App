function contactUsValidation() {
    const name = document.getElementById("full_name").value;
    const email = document.getElementById("email").value;

    const nameError = document.getElementById("name-error");
    const emailError = document.getElementById("email-error");

    // Clear previous error messages
    nameError.textContent = "";
    emailError.textContent = "";

    let isValid = true;
    const nameRegex = /^[A-Za-z\s]+$/;

    // Name validation
    let nameErrors = [];
    if (name.trim() === "") {
        nameErrors.push("*Name can't be empty.");
        isValid = false;
    }
    if (name.length > 35) {
        nameErrors.push("*Name can't be more than 35 characters.");
        isValid = false;
    }
    if (!nameRegex.test(name)) {
        nameErrors.push("*Name should contain only letters.");
        isValid = false;
    }
    if (nameErrors.length > 0) {
        nameError.textContent = nameErrors.join(" ");
    }

    // Email validation
    let emailErrors = [];
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
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
        emailError.textContent = emailErrors.join(" ");
    }

    return isValid;
}

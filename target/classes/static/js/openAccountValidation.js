function openAccountValidation() {
	const idProofInput = document.getElementById("idProof");
	const idProofError = document.getElementById("idProofError");

	idProofError.textContent = "";

	if (idProofInput.files.length === 0) {
		idProofError.textContent = "*Please upload an ID proof.";
		return false;
	}

	return true;
}
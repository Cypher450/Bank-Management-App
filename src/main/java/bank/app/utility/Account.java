package bank.app.utility;

import java.util.Random;

import bank.app.entities.Branch;

public class Account {
	public static String accountNoGenerator(Branch branch) {
		// Generate the first 10 random digits
		Random random = new Random();
		StringBuilder accountNumber = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			accountNumber.append(random.nextInt(10)); // Append random digit between 0-9
		}

		// Get the branch ID and ensure it's 4 digits
		String branchId = String.format("%04d", branch.getBranchId());

		// Append the 4-digit branch ID to the generated account number
		accountNumber.append(branchId);

		return accountNumber.toString();
	}

	public static String ifsccodegenerator(Branch branch) {
		// Get the bank name and make sure it's 6 characters long by appending zeros if
		// needed
		String bankName = branch.getBankName().toUpperCase();
		bankName = String.format("%-6s", bankName).replace(' ', '0'); // Pad with '0' if length is less than 6

		// Get the branch ID and make sure it's 5 digits long
		String branchId = String.format("%05d", branch.getBranchId());

		// Concatenate the bank name and branch ID to form the IFSC code
		String ifscCode = bankName + branchId;

		return ifscCode;
	}
}

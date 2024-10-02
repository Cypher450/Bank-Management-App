package bank.app.dao;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import bank.app.entities.Account;
import bank.app.utility.ByteArrayMultipartFile;

public class AccountRowMapper implements RowMapper<Account> {

	// `account_number`, `ifsc_code`, `customer_id`, `balance`, `opened_date`,
	// `account_type_id`, `id_proof`

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		String accountNumber = rs.getString("account_number");
		String ifscCode = rs.getString("ifsc_code");
		int customerId = rs.getInt("customer_id");
		double balance = rs.getDouble("balance");
		Date openedDate = rs.getDate("opened_date");
		int accountTypeId = rs.getInt("account_type_id");

		// STEP 1 : Retrieve the BLOB data as bytes
		Blob idProof = rs.getBlob("id_proof");
		int blobLength = (int) idProof.length();
		byte[] idProofBytes = idProof.getBytes(1, blobLength);

		// STEP 2 : Convert to MultipartFile
		MultipartFile idProofMultipart = new ByteArrayMultipartFile(idProofBytes, "idProof.jpg", "image/jpeg");

		return new Account(accountNumber, ifscCode, customerId, balance, openedDate, accountTypeId, idProofMultipart);
	}

}

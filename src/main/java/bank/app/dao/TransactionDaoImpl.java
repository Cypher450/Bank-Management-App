package bank.app.dao;

import java.io.IOException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bank.app.entities.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String saveTransaction(String accountNumber, double amount, int transactionTypeId)
			throws SQLException, IOException {

		// Check if it's a withdrawal and if there are sufficient funds
		if (transactionTypeId == 2) {
			double currentBalance = getAccountBalance(accountNumber);
			if (currentBalance < amount) {
				return "Insufficient funds for withdrawal.";
			}
		}

		// Insert transaction into the transaction table
		String insertTransaction = "INSERT INTO transaction (account_no, transaction_type_id, amount, transaction_time) "
				+ "VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(insertTransaction, accountNumber, transactionTypeId, amount,
				new Timestamp(System.currentTimeMillis()));

		// Update the balance in the account table
		String updateBalance = transactionTypeId == 1
				? "UPDATE account SET balance = balance + ? WHERE account_number = ?" // Deposit
				: "UPDATE account SET balance = balance - ? WHERE account_number = ?"; // Withdraw

		jdbcTemplate.update(updateBalance, amount, accountNumber);

		return "Transaction successful.";
	}

	// Get current balance for a specific account
	@SuppressWarnings("deprecation")
	@Override
	public double getAccountBalance(String accountNumber) throws SQLException, IOException {
		String query = "SELECT balance FROM account WHERE account_number = ?";

		return jdbcTemplate.queryForObject(query, new Object[] { accountNumber }, Double.class);
	}

	@Override
	public List<Transaction> getLastTenTransaction(String accountNumber) throws SQLException, IOException {

		String query = "SELECT * FROM transaction WHERE account_no = ? ORDER BY transaction_time DESC LIMIT 10;";

		return jdbcTemplate.query(query, new TransactionRowMapper(), accountNumber);
	}

	@Override
	public List<Transaction> getTransaction(String accountNumber) throws SQLException, IOException {

		String query = "SELECT * FROM transaction WHERE account_no = ? ORDER BY transaction_time DESC;";

		return jdbcTemplate.query(query, new TransactionRowMapper(), accountNumber);

	}

	@Override
	public Double totalCredits(String accountNumber) throws SQLException, IOException {

		String query = "SELECT SUM(amount) FROM transaction WHERE account_no = ? AND transaction_type_id = 1;";

		return jdbcTemplate.queryForObject(query, Double.class, accountNumber);
	}

	@Override
	public Double totalDebits(String accountNumber) throws SQLException, IOException {
		String query = "SELECT SUM(amount) FROM transaction WHERE account_no = ? AND transaction_type_id = 2;";

		return jdbcTemplate.queryForObject(query, Double.class, accountNumber);
	}

}

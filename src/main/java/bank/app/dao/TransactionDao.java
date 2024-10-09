package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import bank.app.entities.Transaction;
public interface TransactionDao {
	String saveTransaction(String accountNumber, double amount, int transactionTypeId)
			throws SQLException, IOException;

	double getAccountBalance(String accountNo) throws SQLException, IOException;
	
	List<Transaction> getLastTenTransaction(String accountNumber) throws SQLException, IOException;
	
	List<Transaction> getTransaction(String accountNumber) throws SQLException, IOException;
	
	Double totalCredits(String accountNumber) throws SQLException, IOException;
	
	Double totalDebits(String accountNumber) throws SQLException, IOException;


}

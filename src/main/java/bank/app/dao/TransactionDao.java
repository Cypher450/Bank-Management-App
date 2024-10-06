package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;
public interface TransactionDao {
	String saveTransaction(String accountNumber, double amount, int transactionTypeId)
			throws SQLException, IOException;

	double getAccountBalance(String accountNo) throws SQLException, IOException;

}

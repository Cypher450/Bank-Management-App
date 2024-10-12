package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import bank.app.entities.Account;

public interface AccountDao {
	int insertCreatedAccount(Account account) throws SQLException, IOException;

	List<Account> getAccountsByCustomerId(int customerId) throws SQLException, IOException;

	List<Account> getAccountDetails(int customerId, int accountTypeId) throws SQLException, IOException;
}

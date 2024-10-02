package bank.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import bank.app.entities.AccountType;

public class AccountTypeRowMapper implements RowMapper<AccountType> {

	@Override
	public AccountType mapRow(ResultSet rs, int rowNum) throws SQLException {

		int accountTypeId = rs.getInt("account_type_id");
		String accountType = rs.getString("account_type");

		return new AccountType(accountTypeId, accountType);
	}
}

package bank.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import bank.app.entities.Transaction;

public class TransactionRowMapper implements RowMapper<Transaction> {

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {

	    int transactionId = rs.getInt("transaction_id");
	    String accountNo = rs.getString("account_no");
	    int transactionTypeId = rs.getInt("transaction_type_id");
	    double amount = rs.getDouble("amount");
	    Timestamp transactionTime = rs.getTimestamp("transaction_time"); 
	    
	    return new Transaction(transactionId, accountNo, transactionTypeId, amount, transactionTime);
	}


	
	
}

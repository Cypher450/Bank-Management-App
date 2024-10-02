package bank.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bank.app.entities.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		int customerId = rs.getInt("customer_id");
		int branchId = rs.getInt("branch_id");

		return new Customer(customerId, branchId);
	}

}

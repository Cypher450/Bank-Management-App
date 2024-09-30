package bank.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bank.app.entities.User;

public class ProfileDetailsRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		String username = rs.getString("username");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		String address = rs.getString("address");
		
		
		return new User(username, firstName, lastName, email, phone, address);
	}

	
	
}

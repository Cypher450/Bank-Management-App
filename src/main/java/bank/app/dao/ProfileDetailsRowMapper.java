package bank.app.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bank.app.entities.User;

public class ProfileDetailsRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user ;
		
		int userId = rs.getInt("user_id");
		String username = rs.getString("username");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		int roleId = rs.getInt("role_id");
		String address = rs.getString("address");
		Date dob = rs.getDate("dob");
		String approvalStatus = rs.getString("approval_status");
		String passwordSalt = rs.getString("salt_password");
		String passwordHash = rs.getString("hashed_password");
	
		
		user = new User(userId, username, firstName, lastName, email, phone, roleId, address, dob, approvalStatus, passwordSalt, passwordHash, passwordHash);
		
		return user;
	}

	
	
}
